package GDSCKNU.CitySavior.service.ai;

import GDSCKNU.CitySavior.exception.AIException;
import GDSCKNU.CitySavior.exception.error.AIError;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
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
                .bodyValue(imgFiles)
                .retrieve()
                .bodyToMono(Integer.class)
                .subscribe(
                        weight::set,
                        throwable -> {
                            throw new AIException(AIError.AI_SERVER_ERROR);
                        });

        return weight.get();
    }
}
