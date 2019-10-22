package com.example.account

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import reactor.kotlin.core.publisher.toFlux
import reactor.kotlin.test.test

/**
 * Created by wonwoo on 2016. 10. 27..
 */

@ExtendWith(MockitoExtension::class)
class AccountServiceTest(@Mock private val accountRepository: AccountRepository) {

    private lateinit var accountService: AccountService

    @BeforeEach
    fun setup() {
        accountService = AccountService(accountRepository)
    }


    @Test
    fun findAll() {

        val accounts = listOf(Account("wonwoo", "123123"), Account("kevin", "pass")).toFlux()
        given(accountRepository.findAll()).willReturn(accounts)
        val findAccounts = accountService.findAll()

        findAccounts.test().assertNext {

            assertThat(it.name).isEqualTo("wonwoo")
            assertThat(it.passwd).isEqualTo("123123")

        }.assertNext {

            assertThat(it.name).isEqualTo("kevin")
            assertThat(it.passwd).isEqualTo("pass")

        }.verifyComplete()

    }

}