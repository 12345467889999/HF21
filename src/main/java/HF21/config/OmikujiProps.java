package HF21.config;

import HF21.utils.OmikujiUtil;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.annotation.PostConstruct;
import java.util.Map;

@ConfigurationProperties(prefix = "omikuji")
public class OmikujiProps {

    private Map<OmikujiUtil.Rank, Integer> weights;

    public Map<OmikujiUtil.Rank, Integer> getWeights() {
        return weights;
    }

    public void setWeights(Map<OmikujiUtil.Rank, Integer> weights) {
        this.weights = weights;
    }

    @PostConstruct
    public void validate() {
        if (weights == null || weights.isEmpty()) {
            weights = OmikujiUtil.classicBalanced();
        }

        long total = 0;
        for (Integer w : weights.values()) {
            if (w == null || w < 0) {
                throw new IllegalArgumentException("权重必须为非负整数");
            }
            total += w;
        }
        if (total <= 0) {
            throw new IllegalArgumentException("权重总和不能为0");
        }
    }
}
