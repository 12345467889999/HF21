package HF21;

import HF21.beans.OmikujiContent;
import HF21.service.OmikujiAiService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TEST {

    @Autowired
    private OmikujiAiService omikujiAiService;

    @Test
    public void testGenerateByRank() {
        try {
            String rank = "大吉"; // 你也可以改成 中吉 / 凶 / 末吉 等看看区别
            OmikujiContent content = omikujiAiService.generateByRank(rank);

            System.out.println("🎋 抽签测试结果 🎋");
            System.out.println("签位: " + rank);
            System.out.println("愿望: " + content.getWish());
            System.out.println("恋爱: " + content.getLove());
            System.out.println("学业: " + content.getStudy());
            System.out.println("工作: " + content.getWork());
            System.out.println("健康: " + content.getHealth());
            System.out.println("旅行: " + content.getTravel());
        } catch (Exception e) {
            System.err.println("❌ 测试失败：" + e.getMessage());
            e.printStackTrace();
        }
    }
}
