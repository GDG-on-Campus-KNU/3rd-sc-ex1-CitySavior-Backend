package GDSCKNU.CitySavior.repository;

import static org.junit.jupiter.api.Assertions.*;

import GDSCKNU.CitySavior.domain.Category;
import GDSCKNU.CitySavior.entity.Report;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ReportRepositoryTest {

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private GeometryFactory geometryFactory;

    @Test
    @DisplayName("인자로 주어지는 반경 안에 값이 존재하면 값을 반환한다.")
    void findReportsWithinRadiusTest() {
        //given
        Report dummyReport1 = Report.builder()
                .weight(1.0)
                .location(geometryFactory.createPoint(new Coordinate(1.0, 1.0)))
                .description("test")
                .img_url("test")
                .repaired_date(null)
                .report_date(LocalDate.now())
                .comments(new ArrayList<>())
                .category(Category.OTHER)
                .build();

        Report dummyReport2 = Report.builder()
                .weight(1.0)
                .location(geometryFactory.createPoint(new Coordinate(2.0, 2.0)))
                .description("test")
                .img_url("test")
                .repaired_date(null)
                .report_date(LocalDate.now())
                .category(Category.BUILD_STRUCTURE)
                .comments(new ArrayList<>())
                .build();

        Point point = geometryFactory.createPoint(new Coordinate(1.0, 1.0));

        //when
        reportRepository.saveAll(List.of(dummyReport1, dummyReport2));
        List<Report> reportsWithinRadiusTest = reportRepository.findReportsWithinRadius(point,
                1000.0);

        //then
        assertEquals(1, reportsWithinRadiusTest.size());
        for (Report report : reportsWithinRadiusTest) {
            System.out.printf("latitude: %f, longitude: %f\n", report.getLocation().getX(),
                    report.getLocation().getY());
        }
    }
}