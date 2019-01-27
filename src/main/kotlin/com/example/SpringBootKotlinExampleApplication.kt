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
open class SpringBootKotlinExampleApplication(private val accountRepository: AccountRepository,
                                              private val messageRepository: MessageRepository) : CommandLineRunner {
    override fun run(vararg p0: String?) {
        accountRepository.deleteAll()
        Stream.of("wonwoo,{noop}123", "user,{noop}456")
                .map { account -> account.split(",") }
                .forEach { name ->
                    val save = Account(name[0], name[1], null)
                    accountRepository.save(save)
                    Stream.of("hi", "wonwoo", "hello")
                            .forEach { message -> messageRepository.save(Message(message, save)) }
                }
    }
}

fun main(args: Array<String>) {
    runApplication<SpringBootKotlinExampleApplication>(*args)
}

