package GDSCKNU.CitySavior.global.postGis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.locationtech.jts.geom.*;

@Configuration
public class GISConfig {

    @Bean
    public GeometryFactory createFactory() {
        return new GeometryFactory(new PrecisionModel(), 4326);
    }
}
