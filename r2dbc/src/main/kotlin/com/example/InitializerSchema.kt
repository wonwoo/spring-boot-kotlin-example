package com.example

import io.r2dbc.spi.ConnectionFactory
import org.springframework.context.annotation.Bean
import org.springframework.core.annotation.Order
import org.springframework.core.io.ClassPathResource
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator
import org.springframework.stereotype.Component

@Component
@Order(10)
class InitializerSchema {

    @Bean
    fun connectionFactoryInitializer(connectionFactory: ConnectionFactory): ConnectionFactoryInitializer {
        return ConnectionFactoryInitializer().apply {
            this.setConnectionFactory(connectionFactory)
            this.setDatabasePopulator(ResourceDatabasePopulator(ClassPathResource("schema.sql")))
        }
    }
}