package GDSCKNU.CitySavior.repository.reportComment;

import GDSCKNU.CitySavior.entity.reportComment.ReportComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportCommentRepository extends JpaRepository<ReportComment, Long> {
}
