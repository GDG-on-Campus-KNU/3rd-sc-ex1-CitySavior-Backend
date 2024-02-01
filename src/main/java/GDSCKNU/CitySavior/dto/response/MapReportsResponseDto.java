package GDSCKNU.CitySavior.dto.response;

import GDSCKNU.CitySavior.domain.Category;
import lombok.Builder;

@Builder
public record MapReportsResponseDto(
        Long reportId,
        double latitude,
        double longitude,
        Category category,
        double weight
) {
}
