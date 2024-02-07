package GDSCKNU.CitySavior.dto.report.response;

import GDSCKNU.CitySavior.domain.Category;
import GDSCKNU.CitySavior.entity.Report;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.Data;

@Data
public class StatisticsResponse {
    private int totalReports;
    private int resolvedReports;
    private Map<Category, StatisticsDetails> statisticsDetails;

    @Data
    private class StatisticsDetails {
        private int totalReports;
        private int resolvedReports;

        private StatisticsDetails() {
            this.totalReports = 0;
            this.resolvedReports = 0;
        }

        private void addTotalReport() {
            this.totalReports++;
        }

        private void addResolvedReport() {
            this.resolvedReports++;
        }
    }

    public StatisticsResponse(List<Report> reports) {
        initStatisticsDetails();
        totalReports = reports.size();
        reports.forEach(report -> {
            Category category = report.getCategory();
            statisticsDetails.get(category).addTotalReport();
            if (report.isResolved()) {
                resolvedReports++;
                statisticsDetails.get(category).addResolvedReport();
            }
        });
    }

    private void initStatisticsDetails() {
        statisticsDetails = Arrays.stream(Category.values())
                .collect(Collectors.toMap(
                        category -> category,
                        category -> new StatisticsDetails(),
                        (details1, details2) -> details1, // If duplicate keys, keep the first one
                        () -> new EnumMap<>(Category.class)
                ));
    }
}
