package com.example.account

import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class AccountService(private val accountRepository: AccountRepository) {

    fun findById(id: Long): Mono<Account> = accountRepository.findById(id)

}
