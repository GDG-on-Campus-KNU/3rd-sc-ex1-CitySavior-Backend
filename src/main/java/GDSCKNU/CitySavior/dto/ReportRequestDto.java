package GDSCKNU.CitySavior.dto;

import GDSCKNU.CitySavior.annotation.CategoryCheck;
import GDSCKNU.CitySavior.domain.Category;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record ReportRequestDto(
        @NotNull(message = "값을 입력해주세요.")
        @PositiveOrZero(message = "0이상의 값을 입력해주세요.")
        double latitude,

        @NotNull(message = "값을 입력해주세요.")
        @PositiveOrZero(message = "0이상의 값을 입력해주세요.")
        double longitude,

        @NotNull(message = "null이 아닌 값을 입력해주세요.")
        String description,

        @CategoryCheck(enumClass = Category.class)
        String category

        ) {
}
