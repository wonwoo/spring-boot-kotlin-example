package com.example.config.service

import com.example.account.Account
import com.example.account.AccountRepository
import com.example.account.UserNotFoundException
import com.example.message.any
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.security.core.userdetails.UserDetailsService


@ExtendWith(MockitoExtension::class)
internal class UserDetailsServiceImplTests(@Mock private val accountRepository: AccountRepository) {

    private lateinit var userDetailsService: UserDetailsService

    @BeforeEach
    fun setup() {
        this.userDetailsService = UserDetailsServiceImpl(accountRepository)
    }


    @Test
    fun `find by username test`() {

        given(accountRepository.findByname(any()))
            .willReturn(Account(name = "wonwoo", passwd = "123123"))

        val userDetails = userDetailsService.loadUserByUsername("wonwoo")

        assertThat(userDetails.username).isEqualTo("wonwoo")
        assertThat(userDetails.password).isEqualTo("123123")

    }

    @Test

    fun `find by username not found test`() {

        given(accountRepository.findByname(any()))
            .willReturn(null)

        assertThatThrownBy {

            userDetailsService.loadUserByUsername("wonwoo")

        }.isInstanceOf(UserNotFoundException::class.java)

            .hasMessage("not found user name : wonwoo")


    }
}