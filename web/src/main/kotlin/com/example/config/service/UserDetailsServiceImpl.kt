package com.example.config.service

import com.example.account.AccountRepository
import com.example.account.UserNotFoundException
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl(private val accountRepository: AccountRepository) : UserDetailsService {

    override fun loadUserByUsername(username: String) = accountRepository.findByname(username)
        ?: throw UserNotFoundException("not found user name : $username ")
}