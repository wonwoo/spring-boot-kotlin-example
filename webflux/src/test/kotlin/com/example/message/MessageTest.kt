package com.example.message

import com.example.account.Account
import com.example.account.AccountRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest
import reactor.core.publisher.Mono
import reactor.test.StepVerifier

/**
 * Created by wonwoo on 2016. 10. 27..
 */
@DataMongoTest
class MessageTest(@Autowired val accountRepository: AccountRepository,
                  @Autowired val messageRepository: MessageRepository) {

    @Test
    fun newMessageHas() {
        val message = this.accountRepository.save(
            Account("wonwoo", "passwd"))
            .map { Message("test message", it) }
            .flatMap {
                this.messageRepository.save(it)
            }


        StepVerifier.create(message).assertNext {

            assertThat(it.message).isEqualTo("test message")

        }.verifyComplete()

    }

    @Test
    fun findAllMessages() {

        val saveMessage: (Account) -> Mono<Message> = {

            messageRepository.deleteAll().then(
                this.messageRepository.save(Message("ok test kotlin", it))
                    .then(this.messageRepository.save(Message("test message", it))))

        }

        val messages = this.accountRepository.save(
            Account("wonwoo", "passwd"))

            .flatMap {

                saveMessage(it)

            }.flatMapMany {

                this.messageRepository.findAll()

            }

        StepVerifier.create(messages).assertNext {

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
                this.messageRepository.save(Message("test message", it))
            }.flatMap {
                this.messageRepository.findById(it.id!!)
            }

        StepVerifier.create(message).assertNext {

            assertThat(it.message).isEqualTo("test message")

        }.verifyComplete()
    }
}