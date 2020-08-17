package com.example.account

import io.reactivex.rxjava3.core.Maybe
import org.springframework.data.repository.reactive.RxJava3CrudRepository

interface AccountRepository : RxJava3CrudRepository<Account, String> {

    fun findByname(name: String): Maybe<Account>

}