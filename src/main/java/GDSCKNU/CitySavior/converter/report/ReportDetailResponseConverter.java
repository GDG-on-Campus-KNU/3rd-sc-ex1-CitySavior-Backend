package GDSCKNU.CitySavior.converter.report;

import GDSCKNU.CitySavior.dto.report.response.ReportDetailResponse;
import GDSCKNU.CitySavior.entity.Report;
import org.springframework.core.convert.converter.Converter;

public class ReportDetailResponseConverter implements Converter<Report, ReportDetailResponse> {
    @Override
    public ReportDetailResponse convert(Report report) {

        return ReportDetailResponse.builder()
                .description(report.getDescription())
                .reportDate(report.getReport_date())
                .repairedDate(report.getRepaired_date())
                .build();
    }
}
