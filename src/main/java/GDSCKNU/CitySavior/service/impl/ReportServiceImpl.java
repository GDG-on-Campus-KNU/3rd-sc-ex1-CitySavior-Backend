package GDSCKNU.CitySavior.service.impl;

import GDSCKNU.CitySavior.dto.ReportDetailResponseDto;
import GDSCKNU.CitySavior.dto.ReportRequestDto;
import GDSCKNU.CitySavior.entity.Report;
import GDSCKNU.CitySavior.repository.ReportRepository;
import GDSCKNU.CitySavior.service.ReportService;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    @Value("${spring.cloud.gcp.storage.url}")
    private String url;

    private final ReportRepository reportRepository;
    private final ModelMapper modelMapper;
    private final GeometryFactory geometryFactory;

    @Override
    public Long saveReport(ReportRequestDto requestDto, double weight, String img_url) {
        Report report = Report.builder()
                .weight(weight)
                .description(requestDto.description())
                .location(geometryFactory.createPoint(new Coordinate(requestDto.longitude(), requestDto.latitude())))
                .img_url(img_url)
                .damage_ratio(requestDto.damageRatio())
                .report_date(LocalDate.now())
                .build();

        Report saveReport = reportRepository.save(report);
        return saveReport.getReport_id();
    }

    @Override
    public ReportDetailResponseDto getReportDetail(Long reportId) {
        Report findReport = reportRepository.findById(reportId)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 신고가 없습니다."));

        ReportDetailResponseDto detailResponseDto = modelMapper.map(findReport, ReportDetailResponseDto.class);
        detailResponseDto.setImg_url(url + findReport.getImg_url());
        return detailResponseDto;
    }
}
