package com.example.account

import kotlinx.coroutines.flow.Flow
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

@Service
class AccountService(private val accountRepository: AccountRepository) {

    fun findAll(): Flow<Account> = accountRepository.findAll()

}
