package com.example.account

import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

@Service
class AccountService(private val accountRepository: AccountRepository) {

    fun findAll(): Flux<Account> = accountRepository.findAll()

}
