package com.example.message

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import java.time.LocalDateTime


data class Message(

    val message: String,

    @Column("account_id")
    val accountId: Long,

    @Column("reg_date")
    val regDate: LocalDateTime = LocalDateTime.now(),

    @Id
    val id: Long? = null

)