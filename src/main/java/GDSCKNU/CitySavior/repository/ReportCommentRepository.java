package GDSCKNU.CitySavior.repository;

import GDSCKNU.CitySavior.entity.ReportComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportCommentRepository extends JpaRepository<ReportComment, Long> {
}
