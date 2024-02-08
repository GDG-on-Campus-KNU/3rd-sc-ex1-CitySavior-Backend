package GDSCKNU.CitySavior.dto.member.response;

import GDSCKNU.CitySavior.dto.achieveMember.response.AchieveMemberResponse;
import java.util.List;

public record MemberInfoResponse(int totalReportCount, int totalRepairedCount, int achieveCollectCount,
                                 List<AchieveMemberResponse> achieveMemberResponses) {
}
