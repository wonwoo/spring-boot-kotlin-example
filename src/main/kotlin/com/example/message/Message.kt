package com.example.message

import com.example.NULL
import com.example.account.Account
import org.hibernate.validator.constraints.NotEmpty
import javax.persistence.*

@Entity
data class Message(
        @get:NotEmpty var message: String? = NULL,

        @ManyToOne(fetch = javax.persistence.FetchType.LAZY)
        var account: Account? = NULL,

        @Id @GeneratedValue var id: Long? = NULL
)