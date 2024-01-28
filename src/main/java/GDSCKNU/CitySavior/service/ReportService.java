package GDSCKNU.CitySavior.service;

import GDSCKNU.CitySavior.dto.MapReportsResponseDto;
import GDSCKNU.CitySavior.dto.ReportDetailResponseDto;
import GDSCKNU.CitySavior.dto.ReportRequestDto;
import java.util.List;
import java.util.Map;

public interface ReportService {

    Long saveReport(ReportRequestDto requestDto, double weight, String img_url);

    ReportDetailResponseDto getReportDetail(Long reportId);

    Map<String, List<MapReportsResponseDto>> getReportsByGIS(double latitude, double longitude);

    void endReport(Long reportId);
}
