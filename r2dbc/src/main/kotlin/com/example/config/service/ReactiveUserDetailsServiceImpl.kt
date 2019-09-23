package com.example.config.service

import com.example.account.AccountRepository
import com.example.account.UserNotFoundException
import org.springframework.security.core.userdetails.ReactiveUserDetailsService
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.ofType
import reactor.kotlin.core.publisher.toMono

@Service
class ReactiveUserDetailsServiceImpl(private val accountRepository: AccountRepository) : ReactiveUserDetailsService {

    override fun findByUsername(username: String): Mono<UserDetails> {

        return accountRepository.findByname(username)
            .switchIfEmpty(UserNotFoundException("not found user name : $username").toMono())
            .ofType()

    }

}