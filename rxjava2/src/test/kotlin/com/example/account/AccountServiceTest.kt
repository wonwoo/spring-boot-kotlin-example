package com.example.account

import io.reactivex.rxkotlin.toFlowable
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class AccountServiceTest(@Mock private val accountRepository: AccountRepository) {

    private lateinit var accountService: AccountService

    @BeforeEach
    fun setup() {
        accountService = AccountService(accountRepository)
    }

    @Test
    fun findAll() {

        val accounts = listOf(Account("wonwoo", "123123"), Account("kevin", "pass")).toFlowable()
        given(accountRepository.findAll()).willReturn(accounts)
        val findAccounts = accountService.findAll()

        findAccounts.test().assertValueAt(0) {
            it == Account("wonwoo", "123123")

        }.assertValueAt(1) {
            it == Account("kevin", "pass")
        }
    }
}