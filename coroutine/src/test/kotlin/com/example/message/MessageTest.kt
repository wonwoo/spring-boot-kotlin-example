package com.example.message

import com.example.account.Account
import com.example.account.AccountRepository
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest

/**
 * Created by wonwoo on 2016. 10. 27..
 */
@DataMongoTest
class MessageTest(private val accountRepository: AccountRepository,
                  private val messageRepository: MessageRepository) {

    @Test
    fun newMessageHas() {

        runBlocking {
            val account = accountRepository.save(Account("wonwoo", "passwd"))

            val message = messageRepository.save(Message("test message", account))

            assertThat(message.message).isEqualTo("test message")
        }
    }

    @Test
    fun findAllMessages() {

        runBlocking {

            val account = accountRepository.save(
                Account("wonwoo", "passwd"))

            messageRepository.deleteAll()
            messageRepository.save(Message("ok test kotlin", account))
            messageRepository.save(Message("test message", account))


            val messages = messageRepository.findAll().toList()

            assertThat(messages[0].message).isEqualTo("ok test kotlin")
            assertThat(messages[1].message).isEqualTo("test message")

        }
    }

    @Test
    fun findByMessage() {

        runBlocking {
            val account = accountRepository.save(
                Account("wonwoo", "passwd"))
            val saveMessage = messageRepository.save(Message("test message", account))
            val message = messageRepository.findById(saveMessage.id!!)

            assertThat(message?.message).isEqualTo("test message")
        }
    }
}