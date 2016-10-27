package com.example.account

import org.hamcrest.CoreMatchers
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.test.context.junit4.SpringRunner

/**
 * Created by wonwoo on 2016. 10. 27..
 */
@RunWith(SpringRunner::class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
open class AccountTest {
    @Autowired
    lateinit var entityManager: TestEntityManager

    @Autowired
    lateinit var accountRepository: AccountRepository

    @Test
    open fun newAccountHasNameAndPassword() {
        val account = Account("wonwoo", "pass123")
        val saved = this.accountRepository.save(account)
        assertThat(saved.name, CoreMatchers.`is`("wonwoo"))
        assertThat(saved.passwd, CoreMatchers.`is`("pass123"))
    }
}