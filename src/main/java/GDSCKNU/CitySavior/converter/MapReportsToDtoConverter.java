package GDSCKNU.CitySavior.converter;


import GDSCKNU.CitySavior.dto.response.MapReportsResponse;
import GDSCKNU.CitySavior.entity.Report;
import org.springframework.core.convert.converter.Converter;

public class MapReportsToDtoConverter implements Converter<Report, MapReportsResponse> {

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
