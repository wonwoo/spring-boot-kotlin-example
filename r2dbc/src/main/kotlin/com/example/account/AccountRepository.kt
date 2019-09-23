package com.example.account

import org.springframework.data.r2dbc.repository.query.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Mono

interface AccountRepository : ReactiveCrudRepository<Account, Long> {

    //not supported
    @Query("select id, name, passwd from account where name = :name")
    fun findByname(name: String): Mono<Account>

}