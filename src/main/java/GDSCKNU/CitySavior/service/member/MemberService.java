package GDSCKNU.CitySavior.service.member;

import GDSCKNU.CitySavior.dto.member.request.MemberCreateV1Request;
import GDSCKNU.CitySavior.entity.member.Member;
import GDSCKNU.CitySavior.exception.MemberException;
import GDSCKNU.CitySavior.exception.error.MemberError;
import GDSCKNU.CitySavior.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void registerMember(MemberCreateV1Request createV1Request) {
        Boolean isExistMember = memberRepository.existsByEmail(createV1Request.email());

        if (isExistMember) {
            throw new MemberException(MemberError.EXIST_MEMBER_EMAIL);
        }

        Member member = Member.builder()
                .email(createV1Request.email())
                .build();

        memberRepository.save(member);
    }
}
