package HF21.controller;


import HF21.service.OmikujiService;
import HF21.vo.HttpResult;
import HF21.vo.OmikujiResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;


@RestController
@CrossOrigin(
        origins = "http://127.0.0.1:5500",
        allowedHeaders = "*",
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}
)
public class testController {

    @Resource
    private OmikujiService omikujiService;

    @PostMapping("/draw")
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


    @GetMapping("/get/{id}")
    public HttpResult<OmikujiResult> getOmikujiById(@PathVariable Integer id) {

        OmikujiResult omikuji =
                omikujiService.getOmikujiById(id);

        if(omikuji == null)
            return HttpResult.error("指定したおみくじは見つかりませんでした");

        return HttpResult.success("success",omikuji);

    }
}
