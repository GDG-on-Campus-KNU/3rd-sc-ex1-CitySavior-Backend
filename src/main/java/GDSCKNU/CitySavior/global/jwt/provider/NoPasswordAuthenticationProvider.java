package GDSCKNU.CitySavior.global.jwt.provider;

import GDSCKNU.CitySavior.entity.memberDetail.MemberDetailsImpl;
import GDSCKNU.CitySavior.global.jwt.entity.NoPasswordAuthenticationToken;
import GDSCKNU.CitySavior.service.member.detail.MemberDetailServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NoPasswordAuthenticationProvider implements AuthenticationProvider {

    private final MemberDetailServiceImpl memberDetailService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        MemberDetailsImpl userDetails = memberDetailService.loadUserByUsername(authentication.getName());
        // 원래는.. 여러 검사를 해야하지만 비밀번호가 없는 관계로 패스
        return new NoPasswordAuthenticationToken(userDetails.getUsername(), null, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return NoPasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
