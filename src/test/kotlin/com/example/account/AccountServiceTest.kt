package com.example.account

import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by wonwoo on 2016. 10. 27..
 */
@RunWith(MockitoJUnitRunner::class)
class AccountServiceTest {

    @Mock
    private lateinit var accountRepository: AccountRepository

    private lateinit var accountService : AccountService

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

}