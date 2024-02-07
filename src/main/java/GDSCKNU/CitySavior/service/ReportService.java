package GDSCKNU.CitySavior.service;

import GDSCKNU.CitySavior.dto.response.MapReportsResponse;
import GDSCKNU.CitySavior.dto.response.ReportDetailResponse;
import GDSCKNU.CitySavior.dto.request.ReportRequest;
import GDSCKNU.CitySavior.dto.response.StatisticsResponse;
import java.util.List;
import java.util.Map;

public interface ReportService {

    Long saveReport(ReportRequest requestDto, double weight, String img_url);

    ReportDetailResponse getReportDetail(Long reportId);

    Map<String, List<MapReportsResponse>> getReportsByGIS(double latitude, double longitude);

    void endReport(Long reportId);

    Long addComment(Long reportId, String content);

    StatisticsResponse getStatistics(double latitude, double longitude);
}
