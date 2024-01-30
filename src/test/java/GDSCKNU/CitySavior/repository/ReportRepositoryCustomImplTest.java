package GDSCKNU.CitySavior.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ReportRepositoryCustomImplTest {

    @Autowired
    private ReportRepository reportRepository;

    @Test
    @DisplayName("카테고리별 통계를 조회한다.")
    void findReportStatistics() {
        // given
        // when
        reportRepository.findReportStatistics();
        // then
    }
}