package GDSCKNU.CitySavior.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportRequestDto {

    private List<MultipartFile> imgFiles;
    private double latitude;
    private double longitude;
    private String description;
    private int categoryId;
    private int damageRatio;
}
