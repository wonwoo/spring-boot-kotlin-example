package com.example.account

import com.example.InitializerSchema
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest
import org.springframework.context.annotation.Import
import reactor.kotlin.test.test

/**
 * Created by wonwoo on 2016. 10. 27..
 */

@DataR2dbcTest
@Import(InitializerSchema::class)
class AccountTest(private val accountRepository: AccountRepository) {


    @Test
    fun newAccountHasNameAndPassword() {
        val account = Account("wonwoo", "pass123")
        val saved = this.accountRepository.save(account)

        saved.test().assertNext {

            assertThat(it.username).isEqualTo("wonwoo")
            assertThat(it.password).isEqualTo("pass123")

        }.verifyComplete()

    }
}