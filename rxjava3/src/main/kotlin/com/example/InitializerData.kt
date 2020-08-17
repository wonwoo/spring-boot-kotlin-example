package com.example

import com.example.account.Account
import com.example.account.AccountRepository
import com.example.message.Message
import com.example.message.MessageRepository
import io.reactivex.rxjava3.core.CompletableSource
import io.reactivex.rxjava3.core.Flowable
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

@Component
class InitializerData(private val accountRepository: AccountRepository,
                      private val messageRepository: MessageRepository) : ApplicationRunner {

    override fun run(args: ApplicationArguments) {

        val accounts = Flowable.fromIterable(listOf("wonwoo,{noop}123", "user,{noop}456"))
                .map { it.split(",") }
                .flatMapSingle {
                    accountRepository.save(Account(it[0], it[1]))
                }

        val message: (Account) -> Flowable<Message> = {

            Flowable.fromIterable(listOf("hi", "wonwoo", "hello"))
                    .flatMapSingle { message ->
                        messageRepository.save(Message(message, it))
                    }
        }

        val deleteAll: CompletableSource = messageRepository.deleteAll()
        accountRepository.deleteAll()
                .andThen(deleteAll)
                .andThen(accounts)
                .flatMap(message).subscribe()

    }

}