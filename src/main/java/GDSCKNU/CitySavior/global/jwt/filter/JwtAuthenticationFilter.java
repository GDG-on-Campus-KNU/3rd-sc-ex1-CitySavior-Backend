package GDSCKNU.CitySavior.global.jwt.filter;

import GDSCKNU.CitySavior.exception.MemberException;
import GDSCKNU.CitySavior.exception.error.MemberError;
import GDSCKNU.CitySavior.global.jwt.exception.JwtException;
import GDSCKNU.CitySavior.global.jwt.exception.error.JwtError;
import GDSCKNU.CitySavior.global.jwt.provider.JwtTokenProvider;
import io.jsonwebtoken.IncorrectClaimException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;
    private static final List<String> PASS_URL = List.of("/v1/auth/signup", "/v1/auth/login");

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        String accessToken = resolveToken(request);

        if (PASS_URL.contains(request.getServletPath())) {
            filterChain.doFilter(request, response);
            return;
        }
        try {
            if (accessToken != null) {
                jwtTokenProvider.validateAccessToken(accessToken);
                Authentication authentication = jwtTokenProvider.getAuthentication(accessToken);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (IncorrectClaimException e) {
            SecurityContextHolder.clearContext();
            throw new JwtException(JwtError.INVALID_JWT_TOKEN);
        } catch (UsernameNotFoundException e) {
            SecurityContextHolder.clearContext();
            throw new MemberException(MemberError.MEMBER_NOT_FOUND_ERROR);
        }
        filterChain.doFilter(request, response);
    }

    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
