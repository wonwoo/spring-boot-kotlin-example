package com.example

import com.example.account.Account
import com.example.account.AccountRepository
import com.example.message.Message
import com.example.message.MessageRepository
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

@Component
class InitializerData(private val accountRepository: AccountRepository,
                      private val messageRepository: MessageRepository) : ApplicationRunner {

    override fun run(args: ApplicationArguments?) {

        listOf("wonwoo,{noop}123", "user,{noop}456")

            .map { it.split(",") }
            .map {

                val save = Account(it[0], it[1])

                accountRepository.save(save)

            }.forEach {

                listOf("hi", "wonwoo", "hello")

                    .forEach { message ->

                        messageRepository.save(Message(message, it))
                    }
            }
    }

}