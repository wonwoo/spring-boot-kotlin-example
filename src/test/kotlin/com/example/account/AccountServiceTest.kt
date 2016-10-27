package com.example.account

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mock
import org.mockito.BDDMockito.*
import org.hamcrest.CoreMatchers.`is`
import org.junit.runner.RunWith
import org.mockito.runners.MockitoJUnitRunner

/**
 * Created by wonwoo on 2016. 10. 27..
 */
@RunWith(MockitoJUnitRunner::class)
class AccountServiceTest {

    @Mock
    lateinit var accountRepository: AccountRepository

    lateinit var accountService : AccountService

    @Before
    fun setup(){
        accountService = AccountService(accountRepository)
    }


    @Test
    fun findAll() {
        val accounts = listOf(Account("wonwoo", "123123"), Account("kevin", "pass"))
        given(accountRepository.findAll()).willReturn(accounts)
        val findAccounts = accountService.findAll()
        assertThat(findAccounts[0].name, `is`("wonwoo"))
        assertThat(findAccounts[1].name, `is`("kevin"))
        assertThat(findAccounts[0].passwd, `is`("123123"))
        assertThat(findAccounts[1].passwd, `is`("pass"))
    }

    @Test
    fun save() {
        val account = Account("wonwoo")
        val accountForm = AccountForm("wonwoo")
        given(accountRepository.save(account)).willReturn(account)
        val saveAccount = accountService.save(accountForm)
        assertThat(saveAccount.name, `is`("wonwoo"))
    }
}