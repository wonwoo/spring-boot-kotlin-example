package com.example.account

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension

/**
 * Created by wonwoo on 2016. 10. 27..
 */

@ExtendWith(MockitoExtension::class)
class AccountServiceTest(@Mock val accountRepository: AccountRepository) {

    private lateinit var accountService : AccountService

    @BeforeEach
    fun setup(){
        accountService = AccountService(accountRepository)
    }


    @Test
    fun findAll() {

        val accounts = listOf(Account("wonwoo", "123123"), Account("kevin", "pass"))
        given(accountRepository.findAll()).willReturn(accounts)
        val findAccounts = accountService.findAll()
        assertThat(findAccounts[0].name).isEqualTo("wonwoo")
        assertThat(findAccounts[1].name).isEqualTo("kevin")
        assertThat(findAccounts[0].passwd).isEqualTo("123123")
        assertThat(findAccounts[1].passwd).isEqualTo("pass")

    }

}