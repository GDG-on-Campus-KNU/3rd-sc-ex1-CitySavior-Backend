package GDSCKNU.CitySavior.dto.report.response;

import GDSCKNU.CitySavior.domain.Category;
import lombok.Builder;

@Builder
public record MapReportsResponse(
        Long reportId,
        double latitude,
        double longitude,
        Category category,
        double weight
) {
}
