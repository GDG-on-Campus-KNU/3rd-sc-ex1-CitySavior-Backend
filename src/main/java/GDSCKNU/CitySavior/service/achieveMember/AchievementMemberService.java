package GDSCKNU.CitySavior.service.achieveMember;

import GDSCKNU.CitySavior.common.AchievementUtil;
import GDSCKNU.CitySavior.dto.achieveMember.response.AchieveMemberResponse;
import GDSCKNU.CitySavior.dto.member.response.MemberInfoResponse;
import GDSCKNU.CitySavior.entity.achieveMember.AchievementMember;
import GDSCKNU.CitySavior.entity.achievement.Achievement;
import GDSCKNU.CitySavior.entity.member.Member;
import GDSCKNU.CitySavior.entity.memberDetail.MemberDetailsImpl;
import GDSCKNU.CitySavior.exception.MemberException;
import GDSCKNU.CitySavior.exception.error.MemberError;
import GDSCKNU.CitySavior.repository.MemberRepository;
import GDSCKNU.CitySavior.repository.ReportRepository;
import GDSCKNU.CitySavior.repository.achieveMember.AchievementMemberRepository;
import GDSCKNU.CitySavior.repository.achievment.AchievementRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AchievementMemberService {

    private final AchievementMemberRepository achievementMemberRepository;
    private final AchievementRepository achievementRepository;
    private final MemberRepository memberRepository;
    private final ReportRepository reportRepository;

    @Transactional(readOnly = true)
    public MemberInfoResponse getMemberInfo(MemberDetailsImpl memberDetails) {
        int countReport = reportRepository.countReportByMemberEmail(memberDetails.getUsername());
        int countRepair = reportRepository.countReportByMemberEmailAndRepaired_dateIsNotNull(
                memberDetails.getUsername());

        int countAchievement = achievementMemberRepository.countAchievementMemberByMember_EmailAndAchievementDateIsNotNull(
                memberDetails.getUsername());

        List<AchieveMemberResponse> achieveMemberResponses = achievementMemberRepository.findAllByMemberEmailAndAchievementDateIsNull(
                memberDetails.getUsername());

        return new MemberInfoResponse(countReport, countRepair, countAchievement, achieveMemberResponses);
    }

    @Transactional
    public void updateAchievementRecord(String categoryName, MemberDetailsImpl memberDetails) {
        Achievement achievement = achievementRepository.findAchievementByCategory(categoryName);

        Boolean existInMemberAchieve = achievementMemberRepository.existsByAchievement_Id(achievement.getId());

        if (existInMemberAchieve) {
            updateRecordProgressCount(achievement.getId());
            return;
        }
        updateNewAchievementMember(achievement, memberDetails);
    }

    @Transactional
    public void updateRecordProgressCount(Long id) {
        AchievementMember achievementMember = achievementMemberRepository.findAchievementMemberByAchievement_Id(id);

        Long progressCount = achievementMember.getProgressCount();
        int checkMaxGoal = AchievementUtil.ACHIEVEMENT_MAX_GOAL - 1;

        if (progressCount < checkMaxGoal) {
            achievementMember.setProgressCountPlus();
        }
        if (progressCount == checkMaxGoal) {
            achievementMember.setAchievementMemberAchieved();
        }
    }

    @Transactional
    public void updateNewAchievementMember(Achievement achievement, MemberDetailsImpl memberDetails) {
        Member member = memberRepository.findByEmail(memberDetails.getUsername())
                .orElseThrow(() -> new MemberException(MemberError.MEMBER_NOT_FOUND_ERROR));

        AchievementMember.builder()
                .achievement(achievement)
                .member(member)
                .achievementDate(null)
                .progressCount(1L);
    }
}