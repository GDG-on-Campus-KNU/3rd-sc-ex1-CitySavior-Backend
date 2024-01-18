package GDSCKNU.CitySavior.service;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

    public String saveFile(String fileName, List<MultipartFile> imgFiles);
}
