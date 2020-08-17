package com.example

import com.example.account.Account
import com.example.config.service.ReactiveUserDetailsServiceImpl.CustomUserDetails
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.test.context.support.WithSecurityContext
import org.springframework.security.test.context.support.WithSecurityContextFactory

@Retention(AnnotationRetention.RUNTIME)
@WithSecurityContext(factory = MockUser.WithMockCustomUserSecurityContextFactory::class)
annotation class MockUser(

    val id: String = "1",

    val username: String = "user",

    val role: Array<String> = [],

    val password: String = "password"

) {

    class WithMockCustomUserSecurityContextFactory : WithSecurityContextFactory<MockUser> {

        override fun createSecurityContext(customUser: MockUser): SecurityContext {

            val grantedAuthorities = mutableListOf<GrantedAuthority>()

                .apply {

                    customUser.role.forEach {

                        this.add(SimpleGrantedAuthority("ROLE_$it"))

                    }
                }

            val authentication = UsernamePasswordAuthenticationToken(
                CustomUserDetails(Account(id = customUser.id, name = customUser.username, passwd = customUser.password)), "N/A", grantedAuthorities)

            return SecurityContextHolder.createEmptyContext().apply {

                this.authentication = authentication

            }
        }
    }

}