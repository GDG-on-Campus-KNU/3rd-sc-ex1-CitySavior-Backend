package GDSCKNU.CitySavior.dto.response;


import GDSCKNU.CitySavior.domain.Category;
import java.util.Map;
import lombok.Builder;

@Builder
public record StatisticsResponseDto (
        int totalReports,
        int resolvedReports,
        Map<Category, StatisticsDetails> statisticsDetails
){
    public record StatisticsDetails(
            int totalReports,
            int resolvedReports
    ){
    }
}
