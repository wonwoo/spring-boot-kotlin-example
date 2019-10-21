package com.example.account

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest
import reactor.kotlin.test.test

@DataR2dbcTest
class AccountRepositoryTests(@Autowired private val accountRepository: AccountRepository)  {

    @Test
    fun `find by name test`() {

        val account = accountRepository.save(Account(name = "wonwoo", passwd = "foo!@#"))
            .flatMap {
                accountRepository.findByname("wonwoo")
            }

        account.test().assertNext {

            assertThat(it.name).isEqualTo("wonwoo")
            assertThat(it.passwd).isEqualTo("foo!@#")

        }.verifyComplete()

    }

}