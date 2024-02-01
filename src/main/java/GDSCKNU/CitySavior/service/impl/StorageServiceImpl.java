package GDSCKNU.CitySavior.service.impl;

import GDSCKNU.CitySavior.service.StorageService;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import java.io.IOException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class StorageServiceImpl implements StorageService {

    @Value("${spring.cloud.gcp.storage.bucket}")
    private String bucketName;

    private final Storage storage;

    @Override
    public String saveFile(MultipartFile imgFiles) {
        String fileName = makeUUIDFileName();
        String fileContentType = imgFiles.getContentType();

        try {
            storage.create(
                    BlobInfo.newBuilder(bucketName, fileName)
                            .setContentType(fileContentType)
                            .build(),
                    imgFiles.getInputStream()
            );

            return fileName;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String makeUUIDFileName() {
        return UUID.randomUUID().toString();
    }
}
