package GDSCKNU.CitySavior.repository;

import GDSCKNU.CitySavior.entity.Report;
import java.util.List;
import java.util.Optional;
import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {

    Optional<Report> findById(Long reportId);

    @Query(value = "SELECT * FROM Report r " +
            "WHERE ST_DWithin(r.location, :point, :radius, false) = true", nativeQuery = true)
    List<Report> findReportsWithinRadius(@Param("point") Point point, @Param("radius") double radius);

}
