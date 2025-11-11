package HF21.controller;


import HF21.config.OmikujiProps;
import HF21.service.OmikujiService;
import HF21.utils.OmikujiUtil;
import HF21.vo.HttpResult;
import HF21.vo.OmikujiResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;


@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class testController {

    @Resource
    private OmikujiService omikujiService;

    @GetMapping("/draw")
    public HttpResult<OmikujiResult> draw() {
        try {
            OmikujiResult result = omikujiService.draw();
            return HttpResult.success("success", result);
        } catch (IOException e) {
            e.printStackTrace();
            return HttpResult.error("error" + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResult.error("system error" + e.getMessage());
        }
    }
}
