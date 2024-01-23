package GDSCKNU.CitySavior.controller;

import GDSCKNU.CitySavior.dto.ReportRequestDto;
import GDSCKNU.CitySavior.service.AIService;
import GDSCKNU.CitySavior.service.ReportService;
import GDSCKNU.CitySavior.service.StorageService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ReportsController {

    private final StorageService storageService;
    private final AIService aiService;
    private final ReportService reportService;

    @PostMapping("/reports")
    public Long report(@RequestPart(name = "imgFiles") MultipartFile imgFiles,
                      @RequestPart(name = "requestDto") ReportRequestDto requestDto) {

        String fileName = storageService.saveFile(imgFiles);
        int damageRate = aiService.evaluateDamageRate(imgFiles);
        return reportService.saveReport(requestDto, damageRate, fileName);
    }
}
