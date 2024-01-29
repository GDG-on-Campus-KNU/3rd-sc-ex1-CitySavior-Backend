package GDSCKNU.CitySavior.converter;

import GDSCKNU.CitySavior.dto.ReportDetailResponseDto.CommentDto;
import GDSCKNU.CitySavior.entity.ReportComment;
import java.util.List;
import org.springframework.core.convert.converter.Converter;

public class ReportCommentsToCommentDtosConverter
        implements Converter<List<ReportComment>, List<CommentDto>> {
    @Override
    public List<CommentDto> convert(List<ReportComment> reportComments) {
        return reportComments.stream()
                .map(reportComment -> CommentDto.builder()
                        .commentId(reportComment.getReport_comment_id())
                        .content(reportComment.getContent())
                        .createdDate(reportComment.getCreate_date())
                        .build())
                .toList();
    }
}
