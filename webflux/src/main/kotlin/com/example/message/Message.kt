package com.example.message

import com.example.account.Account
import org.springframework.data.annotation.Id
import java.time.LocalDateTime


data class Message(

    val message: String,

    val account: Account,

    val regDate: LocalDateTime = LocalDateTime.now(),

    @Id
    val id: String? = null

)