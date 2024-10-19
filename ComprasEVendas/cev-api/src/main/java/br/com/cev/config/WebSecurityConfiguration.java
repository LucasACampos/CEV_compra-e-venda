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

    public static final String JWT_NOT_NECESSARY_ENTRY_POINT = "/**";

    public static final String JWT_BASED_AUTH_ENTRY_POINT = "/api/logged/**";

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
                            JWT_BASED_AUTH_ENTRY_POINT // Para end-points que necessitam que o utilizador esteja logado para acessar, ex: perfil, comprar, etc.
                    ).authenticated();

                    authorizationManagerRequestMatcherRegistry.requestMatchers(
                            JWT_NOT_NECESSARY_ENTRY_POINT
                    ).permitAll();
                })
                //.addFilterBefore(JDKTokenAuthenticatorFilter, UsernamePasswordAuthenticationFilter.class) TODO Criar regras para login e autenticação usando JWT
                .build();

    }


}
