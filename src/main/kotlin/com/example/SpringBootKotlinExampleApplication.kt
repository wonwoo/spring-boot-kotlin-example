package com.example

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(proxyBeanMethods = true)
class SpringBootKotlinExampleApplication

fun main(args: Array<String>) {
    runApplication<SpringBootKotlinExampleApplication>(*args)
}

