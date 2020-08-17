package com.example.account

import com.example.InitializerSchema
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest
import org.springframework.context.annotation.Import
import reactor.kotlin.test.test

@DataR2dbcTest
@Import(InitializerSchema::class)
class AccountRepositoryTests(private val accountRepository: AccountRepository)  {

    @Test
    fun `find by name test`() {

        val account = accountRepository.save(Account(username = "fidel", password = "foo!@#"))
            .flatMap {
                accountRepository.findByname("fidel")
            }

        account.test().assertNext {

            assertThat(it.username).isEqualTo("fidel")
            assertThat(it.password).isEqualTo("foo!@#")

        }.verifyComplete()

    }

}