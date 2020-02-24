package com.example

import com.example.account.Account
import com.example.account.AccountRepository
import com.example.message.Message
import com.example.message.MessageRepository
import kotlinx.coroutines.runBlocking
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

@Component
class InitializerData(private val accountRepository: AccountRepository,
                      private val messageRepository: MessageRepository) : ApplicationRunner {

    override fun run(args: ApplicationArguments?) {

        runBlocking {

            val users = listOf("wonwoo,{noop}123", "user,{noop}456")
                .map { it.split(",") }

                .map {
                    accountRepository.save(Account(it[0], it[1]))
                }

            listOf("hi", "wonwoo", "hello")
                .forEach { message ->
                    users.forEach {
                        messageRepository.save(Message(message, it))
                    }
                }
        }
    }
}