package GDSCKNU.CitySavior.service;

import GDSCKNU.CitySavior.dto.response.MapReportsResponseDto;
import GDSCKNU.CitySavior.dto.response.ReportDetailResponseDto;
import GDSCKNU.CitySavior.dto.request.ReportRequestDto;
import GDSCKNU.CitySavior.dto.response.StatisticsResponseDto;
import java.util.List;
import java.util.Map;

public interface ReportService {

    Long saveReport(ReportRequestDto requestDto, double weight, String img_url);

    ReportDetailResponseDto getReportDetail(Long reportId);

    Map<String, List<MapReportsResponseDto>> getReportsByGIS(double latitude, double longitude);

    void endReport(Long reportId);

    Long addComment(Long reportId, String content);

    StatisticsResponseDto getStatistics(double latitude, double longitude);
}
