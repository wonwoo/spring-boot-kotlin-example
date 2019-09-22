package com.example.account

import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import reactor.core.publisher.Mono

interface AccountRepository : ReactiveMongoRepository<Account, String> {

    fun findByname(name: String): Mono<Account>

}