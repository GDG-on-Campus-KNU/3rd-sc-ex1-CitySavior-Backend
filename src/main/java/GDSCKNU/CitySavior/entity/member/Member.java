package GDSCKNU.CitySavior.entity.member;

import GDSCKNU.CitySavior.entity.Report;
import GDSCKNU.CitySavior.entity.achieveMember.AchievementMember;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String password;

    @Column(nullable = false)
    private boolean isAdmin;

    @Column
    private String fcmToken;

    @OneToMany(mappedBy = "member")
    private List<Report> reports;

    @OneToMany(mappedBy = "member")
    private List<AchievementMember> achievementMembers;

    @Builder
    private Member(Long memberId, String name, String email, String password, boolean isAdmin, String fcmToken) {
        this.memberId = memberId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.isAdmin = isAdmin;
        this.fcmToken = fcmToken;
    }

    public String isAdminMember() {
        if (this.isAdmin) {
            return MemberRole.ADMIN.name();
        }
        return MemberRole.USER.name();
    }
}
