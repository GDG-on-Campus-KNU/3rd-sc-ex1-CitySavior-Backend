package GDSCKNU.CitySavior.repository.achievment;

import GDSCKNU.CitySavior.entity.achievement.Achievement;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AchievementRepository extends JpaRepository<Achievement, Long> {

    List<Achievement> findAll();

    Achievement findAchievementByCategory(String categoryName);
}