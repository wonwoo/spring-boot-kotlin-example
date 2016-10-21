package com.example.account

import com.example.NULL
import org.hibernate.validator.constraints.NotEmpty

data class AccountForm(@get:NotEmpty var name : String? = NULL,
                   var id: Long? = NULL)