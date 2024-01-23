package GDSCKNU.CitySavior.controller;

import GDSCKNU.CitySavior.dto.ReportRequestDto;
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

    @PostMapping("/reports")
    public int report(@RequestPart(name = "imgFiles") MultipartFile imgFiles,
                       @RequestPart(name = "requestDto") ReportRequestDto requestDto) {

        String fileName = storageService.saveFile(imgFiles);

        //TODO: 2024-01-18 신고 아이디 값 반환하도록 수정
        return 0;
    }
}
