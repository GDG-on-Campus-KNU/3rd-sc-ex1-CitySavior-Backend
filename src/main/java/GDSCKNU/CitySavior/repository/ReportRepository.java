package GDSCKNU.CitySavior.repository;

import GDSCKNU.CitySavior.entity.Report;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {

    Optional<Report> findById(Long reportId);

}
