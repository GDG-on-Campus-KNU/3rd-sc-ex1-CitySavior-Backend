package GDSCKNU.CitySavior.service.ai;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

@SpringBootTest
class AIServiceImplTest {

    @Autowired
    private AIService aiService;

    @Test
    @DisplayName("AI서버로부터 가중치를 받아온다.")
    void evaluateDamageRate() throws IOException {
        //given
        MockMultipartFile imgFiles = new MockMultipartFile(
                "image",
                "testImage.png",
                MediaType.IMAGE_PNG_VALUE,
                new FileInputStream("src/test/resources/testImage/testImage.png"));

        //when
        int weight = aiService.evaluateDamageRate(imgFiles);

        //then
        assertTrue(weight >= 0);
    }
}