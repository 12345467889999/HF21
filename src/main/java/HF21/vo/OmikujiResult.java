package HF21.vo;


import HF21.beans.OmikujiContent;
import org.springframework.stereotype.Component;

@Component
public class OmikujiResult {

    // 签位文本：大吉 / 吉 / 凶 ...
    private String rank;

    // 详细签文（愿望、恋爱、学业、工作、健康、旅行）
    private OmikujiContent content;

    public OmikujiResult(String rank, OmikujiContent content) {
        this.rank = rank;
        this.content = content;
    }

    public OmikujiResult() {
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public OmikujiContent getContent() {
        return content;
    }

    public void setContent(OmikujiContent content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "OmikujiResult{" +
                "rank='" + rank + '\'' +
                ", content=" + content +
                '}';
    }
}
