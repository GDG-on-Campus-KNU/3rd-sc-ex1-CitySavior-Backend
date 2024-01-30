package GDSCKNU.CitySavior.service.impl;

import GDSCKNU.CitySavior.domain.Category;
import GDSCKNU.CitySavior.dto.response.MapReportsResponseDto;
import GDSCKNU.CitySavior.dto.response.ReportDetailResponseDto;
import GDSCKNU.CitySavior.dto.request.ReportRequestDto;
import GDSCKNU.CitySavior.dto.response.StatisticsResponseDto;
import GDSCKNU.CitySavior.entity.Report;
import GDSCKNU.CitySavior.entity.ReportComment;
import GDSCKNU.CitySavior.repository.ReportCommentRepository;
import GDSCKNU.CitySavior.repository.ReportRepository;
import GDSCKNU.CitySavior.service.ReportService;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReportServiceImpl implements ReportService {

    @Value("${spring.cloud.gcp.storage.url}")
    private String url;

    private final ReportRepository reportRepository;
    private final ReportCommentRepository reportCommentRepository;

    private final GeometryFactory geometryFactory;
    private final ConversionService conversionService;

    @Override
    public Long saveReport(ReportRequestDto requestDto, double weight, String img_url) {
        Report report = Report.builder()
                .weight(weight)
                .description(requestDto.description())
                .location(geometryFactory.createPoint(new Coordinate(requestDto.longitude(), requestDto.latitude())))
                .img_url(img_url)
                .category(Category.valueOf(requestDto.category()))
                .report_date(LocalDate.now())
                .comments(List.of())
                .build();

        Report saveReport = reportRepository.save(report);
        return saveReport.getReport_id();
    }

    @Override
    public ReportDetailResponseDto getReportDetail(Long reportId) {
        Report findReport = reportRepository.findById(reportId)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 신고가 없습니다."));

        ReportDetailResponseDto responseDto = conversionService.convert(findReport, ReportDetailResponseDto.class);
        List commentDtos = conversionService.convert(findReport.getComments(), List.class);
        responseDto.changeImgUrl(url);
        responseDto.setComments(commentDtos);
        return responseDto;
    }

    @Override
    public Map getReportsByGIS(double latitude, double longitude) {
        List<Report> reports = reportRepository.findReportsWithinRadius(
                geometryFactory.createPoint(
                        new Coordinate(longitude, latitude)), 1000.0);

        List<MapReportsResponseDto> points = reports.stream()
                .map(report -> conversionService.convert(report, MapReportsResponseDto.class))
                .toList();

        return Map.of("points", points);
    }

    @Override
    @Transactional
    public void endReport(Long reportId) {
        Report findReport = reportRepository.findById(reportId)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 신고가 없습니다."));

        findReport.endReport();
    }

    @Override
    @Transactional
    public Long addComment(Long reportId, String content) {
        Report findReport = reportRepository.findById(reportId)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 신고가 없습니다."));

        ReportComment reportComment = ReportComment.builder()
                .create_date(LocalDate.now())
                .report(findReport)
                .content(content)
                .build();

        reportCommentRepository.save(reportComment);
        log.info("reportComment = {}", reportComment.getReport_comment_id());
        findReport.addComment(reportComment);
        return reportComment.getReport_comment_id();
    }

    @Override
    public StatisticsResponseDto getStatistics(double latitude, double longitude) {
        List<Report> reports = reportRepository.findReportsWithinRadius(
                geometryFactory.createPoint(new Coordinate(longitude, latitude)), 1000.0);


        return null;
    }
}
