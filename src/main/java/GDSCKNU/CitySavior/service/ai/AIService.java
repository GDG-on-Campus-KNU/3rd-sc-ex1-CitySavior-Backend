package GDSCKNU.CitySavior.service.ai;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface AIService {

    public int evaluateDamageRate(MultipartFile imgFiles);
}
