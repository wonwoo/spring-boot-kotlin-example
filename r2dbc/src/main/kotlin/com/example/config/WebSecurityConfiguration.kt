package com.example.config

import org.springframework.boot.autoconfigure.security.reactive.PathRequest
import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain

@EnableWebFluxSecurity
class WebSecurityConfiguration {

    @Bean
    fun springSecurityFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain =

        http
            .authorizeExchange {
                it.matchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                it.anyExchange().authenticated()
            }
            .httpBasic()
            .and()
            .formLogin()
            .and()
            .build()
}