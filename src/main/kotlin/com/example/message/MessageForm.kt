package com.example.message

import com.example.NULL
data class MessageForm(
        @get:org.hibernate.validator.constraints.NotEmpty var message: String? = NULL
)