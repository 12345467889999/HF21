package HF21.service;


import HF21.beans.OmikujiContent;

import java.io.IOException;


public interface OmikujiAiService {

     OmikujiContent generateByRank(String rankLabel) throws IOException;
}
