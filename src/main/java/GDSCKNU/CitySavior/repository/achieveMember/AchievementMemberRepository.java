package GDSCKNU.CitySavior.repository.achieveMember;

import GDSCKNU.CitySavior.dto.achieveMember.response.AchieveMemberResponse;
import GDSCKNU.CitySavior.entity.achieveMember.AchievementMember;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AchievementMemberRepository extends JpaRepository<AchievementMember, Long> {

    Boolean existsByAchievement_Id(Long id);

    AchievementMember findAchievementMemberByAchievement_Id(Long id);

    int countAchievementMemberByMember_EmailAndAchievementDateIsNotNull(String email);

    @Query("SELECT NEW GDSCKNU.CitySavior.dto.achieveMember.response.AchieveMemberResponse" +
            "(ach.category, ach.iconUrl, ach.name, ach.description, a.progressCount, ach.goalCount) " +
            "FROM AchievementMember a " +
            "JOIN a.achievement ach " +
            "WHERE a.member.email = :email AND a.achievementDate IS NULL")
    List<AchieveMemberResponse> findAllByMemberEmailAndAchievementDateIsNull(String email);
}
