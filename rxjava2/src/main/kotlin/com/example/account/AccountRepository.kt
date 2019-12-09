package com.example.account

import io.reactivex.Flowable
import io.reactivex.Maybe
import org.springframework.data.repository.reactive.RxJava2CrudRepository

interface AccountRepository : RxJava2CrudRepository<Account, String> {

    fun findByname(name: String): Maybe<Account>

}