package com.example.service.config

import com.example.account.Account
import com.example.account.AccountRepository
import com.example.account.UserNotFoundException
import com.example.any
import com.example.config.service.ReactiveUserDetailsServiceImpl
import com.example.toMaybe
import io.reactivex.Maybe
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.security.core.userdetails.ReactiveUserDetailsService
import reactor.kotlin.test.test


@ExtendWith(MockitoExtension::class)
internal class ReactiveUserDetailsServiceImplTests(@Mock private val accountRepository: AccountRepository) {

    private lateinit var reactiveUserDetailsService: ReactiveUserDetailsService

    @BeforeEach
    fun setup() {
        this.reactiveUserDetailsService = ReactiveUserDetailsServiceImpl(accountRepository)
    }

    @Test
    fun `find by username test`() {
        given(accountRepository.findByname(any()))
            .willReturn(Account(name = "wonwoo", passwd = "123123").toMaybe())


        val userDetails = reactiveUserDetailsService.findByUsername("wonwoo")

        userDetails.test().assertNext {

            assertThat(it.username).isEqualTo("wonwoo")
            assertThat(it.password).isEqualTo("123123")

        }.verifyComplete()

    }

    @Test

    fun `find by username not found test`() {

        given(accountRepository.findByname(any()))
            .willReturn(Maybe.empty())


        val userDetails = reactiveUserDetailsService.findByUsername("wonwoo")

        userDetails.test()
            .expectErrorSatisfies {
                assertThat(it).isInstanceOf(UserNotFoundException::class.java)
                assertThat(it.message).isEqualTo("not found user name : wonwoo")
            }
            .verify()

    }
}


