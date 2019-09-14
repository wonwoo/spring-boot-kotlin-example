package com.example.message

import com.example.account.Account
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import reactor.core.publisher.Flux

interface MessageRepository : ReactiveMongoRepository<Message, String> {
    
    fun findByAccount(account: Account): Flux<Message>

}