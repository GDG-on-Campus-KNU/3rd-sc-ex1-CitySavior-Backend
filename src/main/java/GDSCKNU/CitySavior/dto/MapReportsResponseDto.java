package GDSCKNU.CitySavior.dto;

import GDSCKNU.CitySavior.domain.Category;

public record MapReportsResponseDto(
        Long reportId,
        double latitude,
        double longitude,
        Category category,
        double weight
) {
}
