package GDSCKNU.CitySavior.converter;


import GDSCKNU.CitySavior.dto.response.MapReportsResponseDto;
import GDSCKNU.CitySavior.entity.Report;
import org.springframework.core.convert.converter.Converter;

public class MapReportsToDtoConverter implements Converter<Report, MapReportsResponseDto> {

    @Override
    public MapReportsResponseDto convert(Report reports) {
        return MapReportsResponseDto.builder()
                .reportId(reports.getReport_id())
                .latitude(reports.getLocation().getX())
                .longitude(reports.getLocation().getY())
                .category(reports.getCategory())
                .weight(reports.getWeight())
                .build();
    }
}
