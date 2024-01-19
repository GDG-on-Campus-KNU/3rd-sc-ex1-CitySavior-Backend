package GDSCKNU.CitySavior.service.impl;

import GDSCKNU.CitySavior.service.StorageService;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class StorageServiceImpl implements StorageService {

    @Override
    public String saveFile(String fileName, List<MultipartFile> imgFiles) {
        return null;
    }
}
