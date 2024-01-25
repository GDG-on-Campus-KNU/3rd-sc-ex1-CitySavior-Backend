package GDSCKNU.CitySavior.dto;

import java.time.LocalDate;

public record CommentsDto(
    Long commentId,
    String content,
    LocalDate createdDate) {
}
