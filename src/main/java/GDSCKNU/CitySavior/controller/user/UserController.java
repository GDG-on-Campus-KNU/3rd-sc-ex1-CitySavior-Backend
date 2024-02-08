package GDSCKNU.CitySavior.controller.user;

import GDSCKNU.CitySavior.common.resolver.annotation.UserInfo;
import GDSCKNU.CitySavior.dto.member.response.MemberInfoResponse;
import GDSCKNU.CitySavior.entity.memberDetail.MemberDetailsImpl;
import GDSCKNU.CitySavior.service.achieveMember.AchievementMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final AchievementMemberService achievementMemberService;

    @GetMapping("/info")
    @Transactional
    public ResponseEntity<MemberInfoResponse> getUserInfo(@UserInfo MemberDetailsImpl memberDetails) {
        MemberInfoResponse memberInfoResponse = achievementMemberService.getMemberInfo(memberDetails);

        return ResponseEntity
                .ok(memberInfoResponse);
    }
}
