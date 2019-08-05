package com.example

import com.example.account.Account
import com.example.account.AccountRepository
import com.example.message.Message
import com.example.message.MessageRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.util.stream.Stream

@SpringBootApplication
class SpringBootKotlinExampleApplication(private val accountRepository: AccountRepository,
                                         private val messageRepository: MessageRepository) : CommandLineRunner {
    override fun run(vararg p0: String?) {

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

fun main(args: Array<String>) {
    runApplication<SpringBootKotlinExampleApplication>(*args)
}

