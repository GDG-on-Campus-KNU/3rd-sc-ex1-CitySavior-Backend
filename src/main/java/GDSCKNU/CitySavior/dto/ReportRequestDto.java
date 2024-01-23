package GDSCKNU.CitySavior.dto;

public record ReportRequestDto(
        double latitude,
        double longitude,
        String description,
        String category,
        int damageRatio) {
}
