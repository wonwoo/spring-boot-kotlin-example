package com.example.account

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest
import reactor.kotlin.test.test

/**
 * Created by wonwoo on 2016. 10. 27..
 */

@DataMongoTest
class AccountTest(@Autowired val accountRepository: AccountRepository) {


    @Test
    fun newAccountHasNameAndPassword() {
        val account = Account("wonwoo", "pass123")
        val saved = this.accountRepository.save(account)

        saved.test().assertNext {

            assertThat(it.name).isEqualTo("wonwoo")
            assertThat(it.passwd).isEqualTo("pass123")

        }.verifyComplete()

    }
}