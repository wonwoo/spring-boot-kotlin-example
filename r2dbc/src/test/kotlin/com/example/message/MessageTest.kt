package com.example.message

import com.example.account.Account
import com.example.account.AccountRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest
import reactor.core.publisher.Mono
import reactor.kotlin.test.test

/**
 * Created by wonwoo on 2016. 10. 27..
 */
@DataR2dbcTest
class MessageTest(private val accountRepository: AccountRepository,
                  private val messageRepository: MessageRepository) {

    @Test
    fun newMessageHas() {
        val message = this.accountRepository.save(
            Account("wonwoo", "passwd"))
            .map { Message("test message", it.id!!) }
            .flatMap {
                this.messageRepository.save(it)
            }


        message.test().assertNext {

            assertThat(it.message).isEqualTo("test message")

        }.verifyComplete()

    }

    @Test
    fun findAllMessages() {

        val saveMessage: (Account) -> Mono<Message> = {

            messageRepository.deleteAll().then(
                this.messageRepository.save(Message("ok test kotlin", it.id!!))
                    .then(this.messageRepository.save(Message("test message", it.id!!))))

        }

        val messages = this.accountRepository.save(
            Account("wonwoo", "passwd"))

            .flatMap {

                saveMessage(it)

            }.flatMapMany {

                this.messageRepository.findAll()

            }

        messages.test().assertNext {

            assertThat(it.message).isEqualTo("ok test kotlin")

        }.assertNext {

            assertThat(it.message).isEqualTo("test message")

        }
            .verifyComplete()

    }

    @Test
    fun findByMessage() {
        val message = this.accountRepository.save(
            Account("wonwoo", "passwd"))
            .flatMap {
                this.messageRepository.save(Message("test message", it.id!!))
            }.flatMap {
                this.messageRepository.findById(it.id!!)
            }

        message.test().assertNext {

            assertThat(it.message).isEqualTo("test message")

        }.verifyComplete()
    }
}