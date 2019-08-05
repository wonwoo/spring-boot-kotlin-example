package com.example.account

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class AccountService(private val accountRepository: AccountRepository) {

    @Transactional(readOnly = true)
    fun findAll(): List<Account> = accountRepository.findAll()

}
