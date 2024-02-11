package GDSCKNU.CitySavior.service.member;

import GDSCKNU.CitySavior.dto.member.request.MemberCreateV1Request;
import GDSCKNU.CitySavior.entity.member.Member;
import GDSCKNU.CitySavior.exception.MemberException;
import GDSCKNU.CitySavior.exception.error.MemberError;
import GDSCKNU.CitySavior.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
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
}
