package GDSCKNU.CitySavior.converter.comment;

import GDSCKNU.CitySavior.dto.report.response.ReportDetailResponse.Comment;
import GDSCKNU.CitySavior.entity.reportComment.ReportComment;
import java.util.List;
import org.springframework.core.convert.converter.Converter;

public class CommentConverter
        implements Converter<List<ReportComment>, List<Comment>> {
    @Override
    public List<Comment> convert(List<ReportComment> reportComments) {
        return reportComments.stream()
                .map(reportComment -> Comment.builder()
                        .commentId(reportComment.getReport_comment_id())
                        .content(reportComment.getContent())
                        .createdDate(reportComment.getCreate_date())
                        .build())
                .toList();
    }
}
