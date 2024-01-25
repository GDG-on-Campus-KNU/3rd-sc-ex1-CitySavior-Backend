package GDSCKNU.CitySavior.service.impl;

import GDSCKNU.CitySavior.dto.ReportRequestDto;
import GDSCKNU.CitySavior.entity.Report;
import GDSCKNU.CitySavior.repository.ReportRepository;
import GDSCKNU.CitySavior.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;

    @Override
    public Long saveReport(ReportRequestDto requestDto, double weight, String img_url) {
        Report report = Report.builder()
                .weight(weight)
                .latitude(requestDto.latitude())
                .longitude(requestDto.longitude())
                .description(requestDto.description())
                .img_url(img_url)
                .damage_ratio(requestDto.damageRatio())
                .build();

        Report saveReport = reportRepository.save(report);
        return saveReport.getReport_id();
    }
}
