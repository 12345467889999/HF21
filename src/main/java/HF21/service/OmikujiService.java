package HF21.service;


import HF21.vo.OmikujiResult;

import java.io.IOException;

public interface OmikujiService {

    OmikujiResult draw() throws IOException;
}
