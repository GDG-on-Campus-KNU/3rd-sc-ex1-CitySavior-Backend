package GDSCKNU.CitySavior.service.impl;

import GDSCKNU.CitySavior.service.AIService;
import java.util.List;
import java.util.Random;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class AIServiceImpl implements AIService {

    @Override
    public int evaluateDamageRate(MultipartFile imgFiles) {
        //Todo: AI 모델을 통해 이미지를 분석하여 피해 정도를 반환해야 함
        return new Random().nextInt(10);
    }
}
