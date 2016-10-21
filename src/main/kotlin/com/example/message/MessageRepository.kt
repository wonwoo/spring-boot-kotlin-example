package com.example.message

import com.example.account.Account
import org.springframework.data.jpa.repository.JpaRepository

interface MessageRepository : JpaRepository<Message, Long> {
    fun findByAccount(account: Account?): List<Message>
}