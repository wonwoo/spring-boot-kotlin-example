package com.example.config.service

import com.example.account.Account
import com.example.account.AccountRepository
import com.example.account.UserNotFoundException
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl(private val accountRepository: AccountRepository) : UserDetailsService {

    override fun loadUserByUsername(username: String) = accountRepository.findByname(username)
        ?.let(::CustomUserDetails)
        ?: throw UserNotFoundException("not found user name : $username")


    class CustomUserDetails(val account: Account) : UserDetails {

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