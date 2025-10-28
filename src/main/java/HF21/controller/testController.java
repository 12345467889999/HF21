package HF21.controller;


import HF21.config.OmikujiProps;
import HF21.utils.OmikujiUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
public class testController {

    @Resource
    private  OmikujiProps props;

    @GetMapping("/test")
    public String hello() {
        OmikujiUtil.Rank draw = OmikujiUtil.draw(props.getWeights());
        return "今日の運勢：" + draw.getLabel();
    }
}
