package br.com.cev.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration {

    private static final String SWAGGER_RESOURCES_URL = "**/swagger-resources/**";
    private static final String SWAGGER_UI_URL = "/swagger-ui.html";
    private static final String SWAGGER_DOCS_URL = "/api-docs";
    private static final String SWAGGER_WEBJAR_URL = "/webjars/**";

    public static final String LOGIN_ENTRY_POINT = "/api/auth/login";

    public static final String TOKEN_REFRESH_ENTRY_POINT = "/api/auth/token";

    public static final String TOKEN_NOT_NECESSARY_ENTRY_POINT = "/api/**";

    public static final String TOKEN_BASED_AUTH_ENTRY_POINT = "/api/logged/**";

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .sessionManagement(
                        httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        )
                ).authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> {
                    authorizationManagerRequestMatcherRegistry.requestMatchers(
                            LOGIN_ENTRY_POINT, TOKEN_REFRESH_ENTRY_POINT,
                            TOKEN_NOT_NECESSARY_ENTRY_POINT,
                            SWAGGER_RESOURCES_URL, SWAGGER_UI_URL, SWAGGER_DOCS_URL, SWAGGER_WEBJAR_URL
                    ).permitAll();

                    authorizationManagerRequestMatcherRegistry.requestMatchers(
                            TOKEN_BASED_AUTH_ENTRY_POINT // Para end-points que necessitam que o utilizador esteja logado para acessar, ex: perfil, comprar, etc.
                    ).authenticated();
                })
                //.addFilterBefore(JDKTokenAuthenticatorFilter, UsernamePasswordAuthenticationFilter.class) TODO Criar regras para login e autenticação usando JWT
                .build();

    }


}
