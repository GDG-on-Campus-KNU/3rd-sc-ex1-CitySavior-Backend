package GDSCKNU.CitySavior.config;

import GDSCKNU.CitySavior.converter.comment.CommentConverter;
import GDSCKNU.CitySavior.converter.report.ReportDetailResponseConverter;
import GDSCKNU.CitySavior.converter.report.MapReportsConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new MapReportsConverter());
        registry.addConverter(new ReportDetailResponseConverter());
        registry.addConverter(new CommentConverter());
    }
}
