package GDSCKNU.CitySavior.service.report;

import GDSCKNU.CitySavior.dto.report.response.MapReportsResponse;
import GDSCKNU.CitySavior.dto.report.response.ReportDetailResponse;
import GDSCKNU.CitySavior.dto.report.request.ReportRequest;
import GDSCKNU.CitySavior.dto.report.response.StatisticsResponse;
import java.util.List;
import java.util.Map;

public interface ReportService {

    Long saveReport(ReportRequest requestDto, double weight, String img_url);

    ReportDetailResponse getReportDetail(Long reportId);

    Map<String, List<MapReportsResponse>> getReportsByGIS(double latitude, double longitude, double radius);

    void endReport(Long reportId);

    Long addComment(Long reportId, String content);

    StatisticsResponse getStatistics(double latitude, double longitude);
}
