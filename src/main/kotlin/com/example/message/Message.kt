package com.example.message

import com.example.account.Account
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
data class Message(
        val message: String ,

        @ManyToOne(fetch = javax.persistence.FetchType.LAZY)
        val account: Account,

        @Id @GeneratedValue var id: Long? = null
)