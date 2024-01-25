package GDSCKNU.CitySavior.dto;

import java.time.LocalDate;
import java.util.List;
import lombok.Data;

@Data
public class ReportDetailResponseDto{
    public String description;
    public String img_url;
    public LocalDate reportDate;
    public LocalDate repairedDate;
    public List<CommentsDto> comments;
}
