package HF21.service.impl;

import HF21.beans.OmikujiContent;
import HF21.config.OmikujiProps;
import HF21.service.OmikujiAiService;
import HF21.service.OmikujiService;
import HF21.utils.OmikujiUtil;
import HF21.vo.OmikujiResult;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;


@Service
public class OmikujiServiceImpl implements OmikujiService {


    private final OmikujiProps props;
    private final OmikujiAiService aiService;

    public OmikujiServiceImpl(OmikujiProps props, OmikujiAiService aiService) {
        this.props = props;
        this.aiService = aiService;
    }


    @Override
    public OmikujiResult draw() throws IOException {
        // 1. 按权重算法抽签位
        Map<OmikujiUtil.Rank, Integer> weights = props.getWeights();
        OmikujiUtil.Rank rank = OmikujiUtil.draw(weights);

        // 2. 用签位让 AI 生成日文签文
        OmikujiContent content = aiService.generateByRank(rank.getLabel());

        // 3. 组装结果
        return new OmikujiResult(rank.getLabel(), content);
    }
}
