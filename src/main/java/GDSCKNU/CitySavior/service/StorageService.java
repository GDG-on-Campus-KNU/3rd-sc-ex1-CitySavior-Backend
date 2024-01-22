package GDSCKNU.CitySavior.service;

import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

    public void saveFile(MultipartFile imgFiles);
}
