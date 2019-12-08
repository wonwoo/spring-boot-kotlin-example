package com.example.account

import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Mono

interface AccountRepository : ReactiveCrudRepository<Account, Long> {

    //not supported
    @Query("select id, username, password from account where username = :username")
    fun findByname(name: String): Mono<Account>

}