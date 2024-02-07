package GDSCKNU.CitySavior.entity.achieveMember;

import GDSCKNU.CitySavior.entity.achievement.Achievement;
import GDSCKNU.CitySavior.entity.member.Member;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AchievementMember {

    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "achievement_id", nullable = false)
    @ToString.Exclude
    private Achievement achievement;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    @ToString.Exclude
    private Member member;

    @Column
    private LocalDate achievementDate;

    @Column
    private Long progressCount;

    public void setProgressCountPlus() {
        this.progressCount = progressCount + 1;
    }

    public void setAchievementMemberAchieved() {
        setProgressCountPlus();
        this.achievementDate = LocalDate.now();
    }

    @Builder
    public AchievementMember(Achievement achievement, Member member, LocalDate achievementDate, Long progressCount) {
        this.achievement = achievement;
        this.member = member;
        this.achievementDate = achievementDate;
        this.progressCount = progressCount;
    }
}
