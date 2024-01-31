package GDSCKNU.CitySavior.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import java.io.IOException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class GoogleCloudStorageConfig {

    @Bean
    public Storage storage() throws IOException {

        ClassPathResource resource = new ClassPathResource("citysavior-97b0f402d90c.json");
        GoogleCredentials credentials = GoogleCredentials.fromStream(resource.getInputStream());
        String projectId = "citysavior";
        return StorageOptions.newBuilder()
                .setProjectId(projectId)
                .setCredentials(credentials)
                .build()
                .getService();
    }
}
