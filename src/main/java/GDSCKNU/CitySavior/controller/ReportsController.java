package GDSCKNU.CitySavior.controller;

import GDSCKNU.CitySavior.customAnnotation.HasFile;
import GDSCKNU.CitySavior.dto.reportComment.request.CreateReportCommentRequest;
import GDSCKNU.CitySavior.dto.report.response.MapReportsResponse;
import GDSCKNU.CitySavior.dto.report.response.ReportDetailResponse;
import GDSCKNU.CitySavior.dto.report.request.ReportRequest;
import GDSCKNU.CitySavior.dto.report.response.StatisticsResponse;
import GDSCKNU.CitySavior.service.ai.AIService;
import GDSCKNU.CitySavior.service.report.ReportService;
import GDSCKNU.CitySavior.service.storage.StorageService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
@Slf4j
public class ReportsController {

    private final StorageService storageService;
    private final AIService aiService;
    private final ReportService reportService;

    @GetMapping("/reports/{report_id}")
    public ReportDetailResponse reportDetail(@PathVariable("report_id") Long reportId) {
        return reportService.getReportDetail(reportId);
    }

    @GetMapping("/reports")
    public Map<String, List<MapReportsResponse>> getReportsByGIS(@RequestParam(name = "latitude") double latitude,
                                                                 @RequestParam(name = "longitude") double longitude) {
        return reportService.getReportsByGIS(latitude, longitude);
    }

    @GetMapping("/reports/statistics")
    public StatisticsResponse getStatistics(@RequestParam("latitude") double latitude,
                                            @RequestParam("longitude") double longitude) {
        return reportService.getStatistics(latitude, longitude);
    }

    @PostMapping("/reports")
    public Long report(@RequestPart(name = "imgFiles") @HasFile MultipartFile imgFiles,
                       @RequestPart(name = "requestDto") @Valid ReportRequest requestDto) {

        String fileName = storageService.saveFile(imgFiles);
        int damageRate = aiService.evaluateDamageRate(imgFiles);
        return reportService.saveReport(requestDto, damageRate, fileName);
    }

    @PostMapping("/reports/{report_id}/comment")
    public Long comment(@PathVariable("report_id") Long reportId,
                        @RequestBody CreateReportCommentRequest comment) {
        return reportService.addComment(reportId, comment.comment());
    }

    @PatchMapping("/reports/{reportId}/end")
    public void endReport(@PathVariable("reportId") Long reportId) {
        reportService.endReport(reportId);
    }
}
