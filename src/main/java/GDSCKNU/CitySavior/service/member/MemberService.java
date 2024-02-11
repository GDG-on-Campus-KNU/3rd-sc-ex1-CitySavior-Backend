package GDSCKNU.CitySavior.service.member;

import GDSCKNU.CitySavior.dto.member.request.MemberCreateV1Request;
import GDSCKNU.CitySavior.entity.member.Member;
import GDSCKNU.CitySavior.exception.MemberException;
import GDSCKNU.CitySavior.exception.error.MemberError;
import GDSCKNU.CitySavior.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public void registerMember(MemberCreateV1Request createV1Request) {
        Boolean isExistMember = memberRepository.existsByEmail(createV1Request.email());

        if (isExistMember) {
            throw new MemberException(MemberError.EXIST_MEMBER_EMAIL);
        }

        Member member = Member.builder()
                .email(createV1Request.email())
                .isAdmin(false)
                .build();

        memberRepository.save(member);
    }

    public Member findMemberByEmail(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new MemberException(MemberError.MEMBER_NOT_FOUND_ERROR));
    }
}
