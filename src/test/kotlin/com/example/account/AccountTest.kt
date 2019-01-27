package com.example.account

import org.hamcrest.CoreMatchers
import org.junit.Assert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
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
    private lateinit var entityManager: TestEntityManager

    @Autowired
    private lateinit var accountRepository: AccountRepository

    @Test
    open fun newAccountHasNameAndPassword() {
        val account = Account("wonwoo", "pass123")
        val saved = this.accountRepository.save(account)
        assertThat(saved.name, CoreMatchers.`is`("wonwoo"))
        assertThat(saved.passwd, CoreMatchers.`is`("pass123"))
    }
}