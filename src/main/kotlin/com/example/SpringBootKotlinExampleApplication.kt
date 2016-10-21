package com.example

import com.example.account.Account
import com.example.account.AccountRepository
import com.example.message.Message
import com.example.message.MessageRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import java.util.stream.Stream

val NULL = null
@SpringBootApplication
open class SpringBootKotlinExampleApplication constructor(val accountRepository: AccountRepository, val messageRepository: MessageRepository) : CommandLineRunner{
    override fun run(vararg p0: String?) {
        accountRepository.deleteAll()
        Stream.of("wonwoo,123", "user,456")
         .map { account -> account.split(",") }
        .forEach {
            name -> val save = Account(name[0], name[1])
            accountRepository.save(save)
            Stream.of("hi", "wonwoo", "hello")
                    .forEach { message -> messageRepository.save(Message(message, save)) }}
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(SpringBootKotlinExampleApplication::class.java, *args)
}

