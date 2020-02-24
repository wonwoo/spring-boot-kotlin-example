package com.example.account

import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest

/**
 * Created by wonwoo on 2016. 10. 27..
 */

@DataMongoTest
class AccountTest(private val accountRepository: AccountRepository) {


    @Test
    fun newAccountHasNameAndPassword() {

        runBlocking {
            val account = Account("wonwoo", "pass123")
            val saved = accountRepository.save(account)

            assertThat(saved.name).isEqualTo("wonwoo")
            assertThat(saved.passwd).isEqualTo("pass123")

        }
    }
}