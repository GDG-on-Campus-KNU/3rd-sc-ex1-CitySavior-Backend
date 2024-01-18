package GDSCKNU.CitySavior.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportRequestDto {

    private double latitude;
    private double longitude;
    private String description;
    private int categoryId;
    private int damageRatio;
}
