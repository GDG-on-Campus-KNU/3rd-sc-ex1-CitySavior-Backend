package GDSCKNU.CitySavior.controller;

import GDSCKNU.CitySavior.annotation.HasFile;
import GDSCKNU.CitySavior.dto.MapReportsResponseDto;
import GDSCKNU.CitySavior.dto.ReportDetailResponseDto;
import GDSCKNU.CitySavior.dto.ReportRequestDto;
import GDSCKNU.CitySavior.service.AIService;
import GDSCKNU.CitySavior.service.ReportService;
import GDSCKNU.CitySavior.service.StorageService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping("/reports")
    public Long report(@RequestPart(name = "imgFiles") @HasFile MultipartFile imgFiles,
                       @RequestPart(name = "requestDto") @Valid ReportRequestDto requestDto) {

        String fileName = storageService.saveFile(imgFiles);
        int damageRate = aiService.evaluateDamageRate(imgFiles);
        return reportService.saveReport(requestDto, damageRate, fileName);
    }

    @GetMapping("/reports/{report_id}")
    public ReportDetailResponseDto reportDetail(@PathVariable("report_id") Long reportId) {
        return reportService.getReportDetail(reportId);
    }

    @GetMapping("/reports")
    public Map<String, List<MapReportsResponseDto>> getReportsByGIS(@RequestParam(name = "latitude") double latitude,
                                                                    @RequestParam(name = "longitude") double longitude) {
        return reportService.getReportsByGIS(latitude, longitude);
    }

    @PatchMapping("/reports/{reportId}/end")
    public void endReport(@PathVariable("reportId") Long reportId) {
        reportService.endReport(reportId);
    }
}
