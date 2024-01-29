package GDSCKNU.CitySavior.converter;

import GDSCKNU.CitySavior.dto.ReportDetailResponseDto;
import GDSCKNU.CitySavior.entity.Report;
import org.springframework.core.convert.converter.Converter;

public class ReportToReportDetailResponseDtoConverter implements Converter<Report, ReportDetailResponseDto> {
    @Override
    public ReportDetailResponseDto convert(Report report) {

        return ReportDetailResponseDto.builder()
                .description(report.getDescription())
                .reportDate(report.getReport_date())
                .repairedDate(report.getRepaired_date())
                .build();
    }
}
