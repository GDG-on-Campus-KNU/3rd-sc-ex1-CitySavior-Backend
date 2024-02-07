package GDSCKNU.CitySavior.service.storage;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

@SpringBootTest
class StorageServiceImplTest {

    @Autowired
    private StorageService storageService;

    @Test
    @DisplayName("파일을 저장한다.")
    void saveFile() throws IOException {
        //given
        File file = new File("src/test/resources/testImage/testImage.png");
        MockMultipartFile mockMultipartFile = new MockMultipartFile(
                "file",                         // 파라미터 이름
                "testImage.png",                // 파일 이름
                "image/png",                    // 컨텐츠 타입
                Files.readAllBytes(file.toPath()) // 파일 내용
        );

        //when
        String saveFile = storageService.saveFile(mockMultipartFile);

        //then
        assertNotNull(saveFile);

    }
}