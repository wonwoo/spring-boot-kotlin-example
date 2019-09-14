package com.example.account

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class AccountService(private val accountRepository: AccountRepository) {

    fun findAll(): List<Account> = accountRepository.findAll()

}
