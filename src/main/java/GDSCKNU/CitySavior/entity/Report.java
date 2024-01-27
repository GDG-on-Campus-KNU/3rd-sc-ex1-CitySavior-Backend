package GDSCKNU.CitySavior.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import org.locationtech.jts.geom.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "report_id")
    private Long report_id;
    private double weight;
    @Column(columnDefinition = "geometry(Point, 4326)")
    private Point location;
    private String description;
    private String img_url;
    private double damage_ratio;
    private LocalDate repaired_date;
    private LocalDate report_date;
    @OneToMany(mappedBy = "report")
    private List<ReportComment> comments;
}
