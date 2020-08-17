package com.example.account

import io.reactivex.rxjava3.core.Flowable
import org.springframework.stereotype.Service

@Service
class AccountService(private val accountRepository: AccountRepository) {

    fun findAll(): Flowable<Account> = accountRepository.findAll()

}
