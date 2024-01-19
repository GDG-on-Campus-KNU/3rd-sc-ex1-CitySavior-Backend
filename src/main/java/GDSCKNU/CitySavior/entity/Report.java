package GDSCKNU.CitySavior.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Report {

    @Id
    @Column(name = "report_id")
    private Long report_id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;
    private double weight;
    private double latitude;
    private double longitude;
    private String description;
    private String img_url;
    private double damage_ratio;
    private LocalDate repaired_date;
    private LocalDate report_date;

}
