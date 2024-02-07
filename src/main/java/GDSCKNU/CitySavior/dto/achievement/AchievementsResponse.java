package GDSCKNU.CitySavior.dto.achievement;

import lombok.Builder;

public record AchievementsResponse(String name, String description, String iconUrl, String category, Long goalCount) {

    @Builder
    public AchievementsResponse(String name, String description, String iconUrl, String category, Long goalCount) {
        this.name = name;
        this.description = description;
        this.iconUrl = iconUrl;
        this.category = category;
        this.goalCount = goalCount;
    }
}
