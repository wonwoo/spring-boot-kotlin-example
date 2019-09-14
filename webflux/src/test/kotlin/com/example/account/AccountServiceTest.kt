package com.example.account

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import reactor.core.publisher.Flux
import reactor.test.StepVerifier

/**
 * Created by wonwoo on 2016. 10. 27..
 */

@ExtendWith(MockitoExtension::class)
class AccountServiceTest(@Mock val accountRepository: AccountRepository) {

    private lateinit var accountService: AccountService

    @BeforeEach
    fun setup() {
        accountService = AccountService(accountRepository)
    }


    @Test
    fun findAll() {

        val accounts = Flux.just(Account("wonwoo", "123123"), Account("kevin", "pass"))
        given(accountRepository.findAll()).willReturn(accounts)
        val findAccounts = accountService.findAll()

        StepVerifier.create(findAccounts).assertNext {

            assertThat(it.name).isEqualTo("wonwoo")
            assertThat(it.passwd).isEqualTo("123123")

        }.assertNext {

            assertThat(it.name).isEqualTo("kevin")
            assertThat(it.passwd).isEqualTo("pass")

        }.verifyComplete()

    }

}