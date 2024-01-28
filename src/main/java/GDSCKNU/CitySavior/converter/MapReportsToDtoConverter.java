package GDSCKNU.CitySavior.converter;


import GDSCKNU.CitySavior.dto.MapReportsResponseDto;
import GDSCKNU.CitySavior.entity.Report;
import java.util.List;
import org.springframework.core.convert.converter.Converter;

public class MapReportsToDtoConverter implements Converter<List<Report>, List<MapReportsResponseDto>> {

    @Override
    public List<MapReportsResponseDto> convert(List<Report> reports) {
        return reports.stream()
                .map(report -> new MapReportsResponseDto(
                        report.getReport_id(),
                        report.getLocation().getX(),
                        report.getLocation().getY(),
                        report.getCategory(),
                        report.getWeight()))
                .toList();
    }
}
