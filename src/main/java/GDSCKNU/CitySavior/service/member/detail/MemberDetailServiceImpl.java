package GDSCKNU.CitySavior.service.member.detail;

import GDSCKNU.CitySavior.entity.member.Member;
import GDSCKNU.CitySavior.entity.memberDetail.MemberDetailsImpl;
import GDSCKNU.CitySavior.exception.MemberException;
import GDSCKNU.CitySavior.exception.error.MemberError;
import GDSCKNU.CitySavior.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberDetailServiceImpl implements MemberDetailService {

    private final MemberRepository memberRepository;

    @Override
    public MemberDetailsImpl loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new MemberException(MemberError.MEMBER_NOT_FOUND_ERROR));
        return new MemberDetailsImpl(email, member.isAdminMember());
    }
}
