package GDSCKNU.CitySavior.dto.achieveMember.response;

public record AchieveMemberResponse(String category, String iconUrl, String name, String description,
                                    Long progressCount,
                                    Long goalCount) {
}