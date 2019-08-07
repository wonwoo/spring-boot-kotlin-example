package com.example

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringBootKotlinExampleApplication

fun main(args: Array<String>) {
    runApplication<SpringBootKotlinExampleApplication>(*args)
}

