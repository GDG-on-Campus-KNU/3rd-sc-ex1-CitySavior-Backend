package GDSCKNU.CitySavior.converter.reportComment;

import GDSCKNU.CitySavior.dto.report.response.ReportDetailResponse.comment;
import GDSCKNU.CitySavior.entity.ReportComment;
import java.util.List;
import org.springframework.core.convert.converter.Converter;

public class commentsConverter
        implements Converter<List<ReportComment>, List<comment>> {
    @Override
    public List<comment> convert(List<ReportComment> reportComments) {
        return reportComments.stream()
                .map(reportComment -> comment.builder()
                        .commentId(reportComment.getReport_comment_id())
                        .content(reportComment.getContent())
                        .createdDate(reportComment.getCreate_date())
                        .build())
                .toList();
    }
}
