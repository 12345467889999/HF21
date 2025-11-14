package HF21.service;


import HF21.beans.OmikujiRecord;
import HF21.vo.OmikujiResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.IOException;

public interface OmikujiService extends IService<OmikujiRecord> {

    OmikujiResult draw() throws IOException;

    OmikujiResult getOmikujiById(Integer id);
}
