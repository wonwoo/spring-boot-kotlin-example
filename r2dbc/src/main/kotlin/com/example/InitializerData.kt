package com.example

import com.example.account.Account
import com.example.account.AccountRepository
import com.example.message.Message
import com.example.message.MessageRepository
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux

@Component
class InitializerData(private val accountRepository: AccountRepository,
                      private val messageRepository: MessageRepository) : ApplicationRunner {

    override fun run(args: ApplicationArguments?) {

        val accounts = Flux.just("wonwoo,{noop}123", "user,{noop}456")
            .map { it.split(",") }
            .flatMap {
                accountRepository.save(Account(it[0], it[1]))
            }

        val message: (Account) -> Flux<Message> = {

            Flux.just("hi", "wonwoo", "hello")
                .flatMap { message ->
                    messageRepository.save(Message(message = message, accountId = it.id!!))
                }
        }

        accountRepository.deleteAll()
            .thenMany(messageRepository.deleteAll())
            .thenMany(accounts)
            .flatMap { message(it) }.subscribe()

    }

}