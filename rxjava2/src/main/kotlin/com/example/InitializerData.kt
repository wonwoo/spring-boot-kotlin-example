package com.example

import com.example.account.Account
import com.example.account.AccountRepository
import com.example.message.Message
import com.example.message.MessageRepository
import io.reactivex.Flowable
import io.reactivex.rxkotlin.toFlowable
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

@Component
class InitializerData(private val accountRepository: AccountRepository,
                      private val messageRepository: MessageRepository) : ApplicationRunner {

    override fun run(args: ApplicationArguments) {


        val accounts = listOf("wonwoo,{noop}123", "user,{noop}456")
            .toFlowable()
            .map { it.split(",") }
            .flatMapSingle {
                accountRepository.save(Account(it[0], it[1]))
            }

        val message: (Account) -> Flowable<Message> = {

            listOf("hi", "wonwoo", "hello").toFlowable()
                .flatMapSingle { message ->
                    messageRepository.save(Message(message, it))
                }
        }

        accountRepository.deleteAll()
            .andThen(messageRepository.deleteAll())
            .andThen(accounts)
            .flatMap(message).subscribe()

    }

}