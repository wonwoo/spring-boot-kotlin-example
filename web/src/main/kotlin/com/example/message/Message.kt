package com.example.message

import com.example.account.Account
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
data class Message(

    val message: String,

    @ManyToOne(fetch = FetchType.LAZY)
    val account: Account,

    val regDate: LocalDateTime = LocalDateTime.now(),

    @Id
    @GeneratedValue
    var id: Long? = null

)