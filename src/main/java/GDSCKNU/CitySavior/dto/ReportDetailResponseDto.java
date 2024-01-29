package GDSCKNU.CitySavior.dto;

import java.time.LocalDate;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReportDetailResponseDto{
    private String description;
    private String img_url;
    private LocalDate reportDate;
    private LocalDate repairedDate;
    private List<CommentDto> comments;

    @Builder
    public record CommentDto(
        Long commentId,
        String content,
        LocalDate createdDate) {
    }

    public void changeImgUrl(String url){
        this.img_url += url;
    }



}
