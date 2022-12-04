package de.rowi1de.reactive.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain

@EnableWebFluxSecurity
@Configuration
class SecurityConfig {

    @Bean
    fun springSecurityFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain =
        http.apply {
            csrf()
                .disable()
            authorizeExchange()
                // graphql
                .pathMatchers(
                    "/graphql", // single graphql POST Endpoint for query / mutations /  introspection
                    "/playground", // playground
                    "/sdl", // schema
                    "/subscriptions" // apollo compatible websocket subscription
                )
                .permitAll()
                .and()
                // REST & OpenAPI, Actuator
                .authorizeExchange()
                .pathMatchers(
                    "/rest/**",
                    "/api-docs/**", // OpenAPI
                    "/api-docs.yaml", // OpenAPI
                    "/swagger-ui.html", // OpenAPI
                    "/webjars/**", // OpenAPI
                    "/actuator/health/**" // Actuator
                )
                .permitAll()
                .anyExchange()
                .denyAll()
                .and()
        }
            .build()
}
