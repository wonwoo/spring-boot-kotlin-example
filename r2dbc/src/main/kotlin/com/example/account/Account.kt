package com.example.account

import org.springframework.data.annotation.Id

data class Account(

    val username: String,

    val password: String,

    @Id
    val id: Long? = null

)
