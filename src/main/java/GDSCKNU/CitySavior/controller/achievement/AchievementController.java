package GDSCKNU.CitySavior.controller.achievement;

import GDSCKNU.CitySavior.dto.achievement.AchievementsResponse;
import GDSCKNU.CitySavior.entity.achievement.Achievement;
import GDSCKNU.CitySavior.repository.achievment.AchievementRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/achievement")
@RequiredArgsConstructor
public class AchievementController {

    private final AchievementRepository achievementRepository;

    @GetMapping() // merge 하면 ApiResponse 로 바꾸기
    @Transactional(readOnly = true)
    public ResponseEntity<List<AchievementsResponse>> getAchievementList() {
        List<AchievementsResponse> achievement = achievementRepository.findAll()
                .stream()
                .map(Achievement::toAchievementResponse)
                .toList();

        return ResponseEntity
                .ok(achievement);
    }
}
