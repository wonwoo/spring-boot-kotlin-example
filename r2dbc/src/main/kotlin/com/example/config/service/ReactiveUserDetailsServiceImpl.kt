package com.example.config.service

import com.example.account.Account
import com.example.account.AccountRepository
import com.example.account.UserNotFoundException
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.ReactiveUserDetailsService
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.switchIfEmpty
import reactor.kotlin.core.publisher.toMono

@Service
class ReactiveUserDetailsServiceImpl(private val accountRepository: AccountRepository) : ReactiveUserDetailsService {

    override fun findByUsername(username: String): Mono<UserDetails> {

        return accountRepository.findByname(username)
            .switchIfEmpty { UserNotFoundException("not found user name : $username").toMono() }
            .map { CustomUserDetails(it) }

    }

    class CustomUserDetails(private val account: Account) : UserDetails {

        fun getId() = account.id

        override fun getPassword(): String = account.password

        private fun authorities() =

            mutableListOf(SimpleGrantedAuthority("ROLE_USER")).apply {

                if (account.username == "wonwoo") {

                    this.add(SimpleGrantedAuthority("ROLE_ADMIN"))

                }
            }

        override fun getAuthorities() = authorities()

        override fun getUsername(): String = account.username

        override fun isAccountNonExpired(): Boolean = true

        override fun isAccountNonLocked(): Boolean = true

        override fun isCredentialsNonExpired(): Boolean = true

        override fun isEnabled(): Boolean = true
    }

}