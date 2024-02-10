package GDSCKNU.CitySavior.converter.report;


import GDSCKNU.CitySavior.dto.report.response.MapReportsResponse;
import GDSCKNU.CitySavior.entity.report.Report;
import org.springframework.core.convert.converter.Converter;

public class MapReportsConverter implements Converter<Report, MapReportsResponse> {

    @Override
    public MapReportsResponse convert(Report reports) {
        return MapReportsResponse.builder()
                .reportId(reports.getReport_id())
                .latitude(reports.getLocation().getX())
                .longitude(reports.getLocation().getY())
                .category(reports.getCategory())
                .weight(reports.getWeight())
                .build();
    }
}
