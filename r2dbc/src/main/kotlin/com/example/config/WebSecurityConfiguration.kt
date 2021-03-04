package com.example.config

import org.springframework.boot.autoconfigure.security.reactive.PathRequest
import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.config.web.server.invoke
import org.springframework.security.web.server.SecurityWebFilterChain

@EnableWebFluxSecurity
class WebSecurityConfiguration {

    @Bean
    fun springSecurityFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain = http {
        authorizeExchange {
            authorize(PathRequest.toStaticResources().atCommonLocations(), permitAll)
            authorize(anyExchange, authenticated)
        }
        httpBasic {
        }
        formLogin {
        }
    }
}