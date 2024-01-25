package GDSCKNU.CitySavior.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UtilityConfig {

    @Bean
    public ModelMapper staticModelMapper() {
        return new ModelMapper();
    }
}
