package GDSCKNU.CitySavior.service;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface AIService {

    public int evaluateDamageRate(List<MultipartFile> imgFiles);
}
