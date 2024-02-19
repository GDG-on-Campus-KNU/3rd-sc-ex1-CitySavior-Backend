package GDSCKNU.CitySavior.global.security.config;

import GDSCKNU.CitySavior.global.jwt.filter.JwtAuthenticationFilter;
import GDSCKNU.CitySavior.global.jwt.filter.NoPasswordAuthenticationFilter;
import GDSCKNU.CitySavior.global.jwt.provider.JwtTokenProvider;
import GDSCKNU.CitySavior.global.jwt.provider.NoPasswordAuthenticationProvider;
import GDSCKNU.CitySavior.service.member.detail.MemberDetailServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.FrameOptionsConfig;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;

    private final MemberDetailServiceImpl memberDetailService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        (authorizeHttpRequests) -> authorizeHttpRequests
                                .requestMatchers("/v1/auth/login", "/v1/auth/signup", "/v1/auth/reissue").permitAll()
                                .anyRequest().authenticated())
                .addFilterBefore(noPasswordAuthenticationFilter(http), UsernamePasswordAuthenticationFilter.class)
                .addFilterAfter(new JwtAuthenticationFilter(jwtTokenProvider),
                        UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(
                        (sessionManagement) -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .headers((headers) -> headers.frameOptions(FrameOptionsConfig::sameOrigin));

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(
                AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(authenticationProvider());
        return authenticationManagerBuilder.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        return new NoPasswordAuthenticationProvider(memberDetailService);
    }

    @Bean
    public NoPasswordAuthenticationFilter noPasswordAuthenticationFilter(HttpSecurity http) throws Exception {
        NoPasswordAuthenticationFilter noPasswordAuthenticationFilter = new NoPasswordAuthenticationFilter(
                new AntPathRequestMatcher("/v1/login", HttpMethod.POST.name()));

        noPasswordAuthenticationFilter.setAuthenticationManager(authenticationManager(http));
        return noPasswordAuthenticationFilter;
    }
}
