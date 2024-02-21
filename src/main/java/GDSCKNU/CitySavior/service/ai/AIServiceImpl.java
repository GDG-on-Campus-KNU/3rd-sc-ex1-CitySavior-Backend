package GDSCKNU.CitySavior.service.ai;

import GDSCKNU.CitySavior.exception.AIException;
import GDSCKNU.CitySavior.exception.error.AIError;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class AIServiceImpl implements AIService {

    private final WebClient webClient;

    @Value("${ai.server.uri}")
    private String aiServerUri;

    @Value("${ai.server.path}")
    private String aiServerPath;


    @Override
    public int evaluateDamageRate(MultipartFile imgFiles) {
        AtomicInteger weight = new AtomicInteger();

        webClient.post()
                .uri(aiServerUri + aiServerPath)
                .body(BodyInserters.fromMultipartData(makeFormData(imgFiles)))
                .retrieve()
                .bodyToMono(Integer.class)
                .subscribe(
                        weight::set,
                        throwable -> {
                            throw new AIException(AIError.AI_SERVER_ERROR);
                        });

        return weight.get();
    }

    private MultiValueMap<String, Object> makeFormData(MultipartFile imgFiles) {
        MultiValueMap<String, Object> formData = new LinkedMultiValueMap<>();
        try{
            formData.add("image", new ByteArrayResource(imgFiles.getBytes()) {
                @Override
                public String getFilename() {
                    return "image.jpg";
                }
            });
        } catch (IOException e) {
            throw new AIException(AIError.AI_SERVER_ERROR);
        }
        return formData;
    }

}
