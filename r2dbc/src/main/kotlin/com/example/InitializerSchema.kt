package com.example

import io.r2dbc.spi.ConnectionFactory
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.core.annotation.Order
import org.springframework.core.io.ResourceLoader
import org.springframework.data.r2dbc.connectionfactory.init.ResourceDatabasePopulator
import org.springframework.stereotype.Component

@Component
@Order(10)
class InitializerSchema(private val connectionFactory: ConnectionFactory,
                        private val resourceLoader: ResourceLoader) : ApplicationRunner {

    override fun run(args: ApplicationArguments) {
        val scripts = resourceLoader.getResource("classpath:schema.sql")
        ResourceDatabasePopulator(scripts).execute(connectionFactory).block()
    }

}