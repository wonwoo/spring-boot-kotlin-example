package com.example.account

import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest
import java.util.concurrent.TimeUnit


@DataMongoTest
class AccountTest(private val accountRepository: AccountRepository) {

    @Test
    fun newAccountHasNameAndPassword() {
        val account = Account("wonwoo", "pass123")
        val saved = this.accountRepository.save(account)

        saved.test().awaitDone(5, TimeUnit.SECONDS).assertValueCount(1).assertValue {
            it.name == "wonwoo"
        }.assertValue {
            it.passwd == "pass123"
        }
    }
}