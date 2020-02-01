package com.example.message

import com.example.account.Account
import com.example.account.AccountRepository
import io.reactivex.Single
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest
import java.util.concurrent.TimeUnit


@DataMongoTest
class MessageTest(private val accountRepository: AccountRepository,
                  private val messageRepository: MessageRepository) {

    @Test
    fun newMessageHas() {
        val message = this.accountRepository.save(
            Account("wonwoo", "passwd"))
            .map { Message("test message", it) }
            .flatMap {
                this.messageRepository.save(it)
            }


        message.test().awaitDone(5, TimeUnit.SECONDS).assertValueCount(1).assertValue {

            it.message == "test message"

        }

    }

    @Test
    fun findAllMessages() {

        val saveMessage: (Account) -> Single<Message> = {
            messageRepository.deleteAll().andThen(this.messageRepository.save(Message("test message", it)).ambWith(this.messageRepository.save(Message("ok test kotlin", it))))
        }

        val messages = this.accountRepository.save(
            Account("wonwoo", "passwd"))

            .flatMap {

                saveMessage(it)

            }.flatMapPublisher {

                this.messageRepository.findAll()

            }

        messages.test().awaitDone(5, TimeUnit.SECONDS).assertValueAt(0) {

            it.message == "test message"

        }.assertValueAt(1) {

            it.message == "ok test kotlin"

        }

    }

    @Test
    fun findByMessage() {
        val message = this.accountRepository.save(
            Account("wonwoo", "passwd"))
            .flatMap {
                this.messageRepository.save(Message("test message", it))
            }.flatMapMaybe {
                this.messageRepository.findById(it.id!!)
            }

        message.test().awaitDone(5, TimeUnit.SECONDS).assertValueCount(1).assertValue {
            it.message == "test message"

        }
    }
}