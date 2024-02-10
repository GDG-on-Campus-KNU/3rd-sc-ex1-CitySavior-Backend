package GDSCKNU.CitySavior.service.ai;

import GDSCKNU.CitySavior.service.ai.AIService;
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
