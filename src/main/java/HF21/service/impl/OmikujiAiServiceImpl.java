package HF21.service.impl;

import HF21.beans.OmikujiContent;
import HF21.config.OpenAiProps;
import HF21.service.OmikujiAiService;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import okhttp3.*;

import java.io.IOException;


@Service
public class OmikujiAiServiceImpl implements OmikujiAiService {

    private static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    private final OkHttpClient httpClient = new OkHttpClient();
    private final ObjectMapper mapper = new ObjectMapper();
    private final OpenAiProps props;


    public OmikujiAiServiceImpl(OpenAiProps props) {
        this.props = props;
    }


    /**
     * 根据签位生成各项签文内容
     *
     * @param rankLabel 签位文本，比如 "大吉" / "中吉" / "凶"
     */
    @Override
    public OmikujiContent generateByRank(String rankLabel) throws IOException {
        ArrayNode messages = mapper.createArrayNode();

        ObjectNode system = mapper.createObjectNode();
        system.put("role", "system");
        system.put("content",
                "あなたは日本の神社でおみくじを書く巫女です。" +
                        "出力は JSON 形式で、項目は wish, love, study, work, health, travel です。" +
                        "各項目は運勢の内容を2～3文の自然な日本語で書いてください。" +
                        "文体は穏やかで礼儀正しく、少し古風で神聖な雰囲気を持たせます。" +
                        "ただし、文章の始まりに「〜においては」「〜については」などの硬い表現を絶対に使わないで、" +
                        "より自然で詩的な文にしてください。" +
                        "改行せずに出力してください。" +
                        "JSON 以外の文字は出力しないでください。"
        );
        messages.add(system);

        ObjectNode user = mapper.createObjectNode();
        user.put("role", "user");
        user.put("content",
                "おみくじの種類は「" + rankLabel + "」です。" +
                        buildToneHint(rankLabel) +
                        "願望(wish)、恋愛(love)、学業(study)、仕事(work)、健康(health)、旅行(travel)の六項目について、" +
                        "それぞれにふさわしい日本語の運勢文を作成してください。" +
                        "文章の始まりに「〜においては」「〜については」などの硬い表現を絶対に使わないでください");
        messages.add(user);

        ObjectNode body = mapper.createObjectNode();
        body.put("model", "gpt-4o");
        body.set("messages", messages);
        body.putObject("response_format").put("type", "json_object");
        body.put("max_tokens", 500);
        body.put("temperature", 0.7);

        Request request = new Request.Builder()
                .url("https://api.openai.com/v1/chat/completions")
                .addHeader("Authorization", "Bearer " + props.getApiKey())
                .addHeader("Content-Type", "application/json")
                .post(RequestBody.create(mapper.writeValueAsString(body), JSON))
                .build();

        Response response = httpClient.newCall(request).execute();
        try {
            if (!response.isSuccessful()) {
                throw new IOException("OpenAI API error: " + response.code() + " " + response.message());
            }
            String respStr = response.body().string();
            JsonNode root = mapper.readTree(respStr);
            String contentStr = root.path("choices").get(0)
                    .path("message").path("content").asText();

            return mapper.readValue(contentStr, OmikujiContent.class);
        } finally {
            if (response != null) {
                response.close();
            }
        }
    }


    /**
     * 根据签位的不同进行不同的回应
     * @param rankLabel 签位文本，比如 "大吉" / "中吉" / "凶"
     * @return 大体的签文氛围
     */
    private String buildToneHint(String rankLabel) {
        if ("大吉".equals(rankLabel)) {
            return "全体的にとても吉兆で、希望と光に満ちた内容にしてください。読む人の心が安らぎ、喜びを感じられるように書いてください。";
        }
        if ("中吉".equals(rankLabel) || "小吉".equals(rankLabel) || "吉".equals(rankLabel)) {
            return "全体的に良い運勢で、順調な部分と注意すべき点の両方を穏やかに伝える内容にしてください。";
        }
        if ("末吉".equals(rankLabel)) {
            return "やや不安定な運勢ですが、努力や反省によって好転できるという希望を込めた内容にしてください。";
        }
        if ("凶".equals(rankLabel) || "大凶".equals(rankLabel)) {
            return "全体的に運気が低く、困難や注意点を示す内容にしてください。ただし、冷静に受け止め、改善や成長のきっかけを与えるような言葉で締めくくってください。";
        }
        return "おみくじの種類に合わせて、吉凶や雰囲気を自然に調整してください。";
    }

}
