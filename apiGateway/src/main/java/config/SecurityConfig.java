package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    // Define a SecurityWebFilterChain bean to configure security for the application
    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity serverHttpSecurity) {
        serverHttpSecurity
                // Disable Cross-Site Request Forgery (CSRF) protection
                .csrf(ServerHttpSecurity.CsrfSpec::disable)

                // Configure authorization rules for different paths
                .authorizeExchange(exchange ->
                        exchange.pathMatchers("/eureka/**")
                                .permitAll()  // Allow unauthenticated access to paths starting with "/eureka/**"
                                .anyExchange()
                                .authenticated())  // Require authentication for any other path

                // Configure OAuth 2.0 Resource Server with JSON Web Token (JWT) authentication
                .oauth2ResourceServer(spec -> spec.jwt(Customizer.withDefaults()));

        // Return the configured SecurityWebFilterChain
        return serverHttpSecurity.build();
    }
}
