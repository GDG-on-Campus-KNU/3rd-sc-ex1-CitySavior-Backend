package GDSCKNU.CitySavior.service;

import GDSCKNU.CitySavior.dto.ReportDetailResponseDto;
import GDSCKNU.CitySavior.dto.ReportRequestDto;

public interface ReportService {

    Long saveReport(ReportRequestDto requestDto, double weight, String img_url);

    ReportDetailResponseDto getReportDetail(Long reportId);
}
