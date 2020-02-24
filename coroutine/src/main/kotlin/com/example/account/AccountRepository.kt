package com.example.account

import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface AccountRepository : CoroutineCrudRepository<Account, String> {

    suspend fun findByname(name: String): Account?

}