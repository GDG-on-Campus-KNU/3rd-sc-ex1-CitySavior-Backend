package GDSCKNU.CitySavior.global.jwt.filter;

import GDSCKNU.CitySavior.dto.member.request.MemberLoginV1Request;
import GDSCKNU.CitySavior.global.jwt.entity.NoPasswordAuthenticationToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import lombok.SneakyThrows;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.StreamUtils;


public class NoPasswordAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public NoPasswordAuthenticationFilter(RequestMatcher requiresAuthenticationRequestMatcher) {
        super(requiresAuthenticationRequestMatcher);
    }

    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        MemberLoginV1Request memberLoginV1Request = objectMapper.readValue(messageBody, MemberLoginV1Request.class);

        String email = memberLoginV1Request.email();

        NoPasswordAuthenticationToken authentication = new NoPasswordAuthenticationToken(email, null, null);

        return getAuthenticationManager().authenticate(authentication);
    }
}
