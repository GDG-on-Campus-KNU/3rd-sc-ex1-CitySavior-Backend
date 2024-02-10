package GDSCKNU.CitySavior.entity.report;

import GDSCKNU.CitySavior.domain.Category;
import GDSCKNU.CitySavior.entity.reportComment.ReportComment;
import GDSCKNU.CitySavior.entity.member.Member;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.locationtech.jts.geom.Point;

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
    @Enumerated(value = EnumType.STRING)
    private Category category;
    private LocalDate repaired_date;
    private LocalDate report_date;
    @OneToMany(mappedBy = "report")
    private List<ReportComment> comments;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    @ToString.Exclude
    private Member member;

    public void endReport() {
        this.repaired_date = LocalDate.now();
    }

    public void addComment(ReportComment comment) {
        this.comments.add(comment);
    }

    public boolean isResolved() {
        return this.repaired_date != null;
    }
}