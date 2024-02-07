package GDSCKNU.CitySavior.global.mvc;

import GDSCKNU.CitySavior.converter.reportComment.commentsConverter;
import GDSCKNU.CitySavior.converter.report.ReportDetailResponseConverter;
import GDSCKNU.CitySavior.converter.report.MapReportsResponseConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new MapReportsResponseConverter());
        registry.addConverter(new ReportDetailResponseConverter());
        registry.addConverter(new commentsConverter());
    }
}
