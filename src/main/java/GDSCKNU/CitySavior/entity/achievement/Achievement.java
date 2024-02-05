package GDSCKNU.CitySavior.entity.achievement;

import GDSCKNU.CitySavior.dto.response.AchievementsResponse;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


@ToString
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Achievement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    @Column
    private String iconUrl;

    @Column
    private String category;

    @Column
    private Long goalCount;

    public AchievementsResponse toAchievementResponse() {
        return AchievementsResponse.builder()
                .name(this.name)
                .description(this.description)
                .iconUrl(this.iconUrl)
                .category(this.category)
                .goalCount(this.goalCount)
                .build();
    }

}
