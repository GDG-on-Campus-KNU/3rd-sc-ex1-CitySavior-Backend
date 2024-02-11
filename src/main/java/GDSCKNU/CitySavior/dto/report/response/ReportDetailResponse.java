package GDSCKNU.CitySavior.dto.report.response;

import java.time.LocalDate;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReportDetailResponse {
    private String description;
    private String img_url;
    private LocalDate reportDate;
    private LocalDate repairedDate;
    private List<Comment> comments;

    @Builder
    public record Comment(
        Long commentId,
        String content,
        LocalDate createdDate) {
    }

    public void changeImgUrl(String url){
        this.img_url = url + this.img_url;
    }



}
