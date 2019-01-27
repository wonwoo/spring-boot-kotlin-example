package com.example.message

import javax.validation.constraints.NotEmpty

data class MessageForm(@get:NotEmpty val message: String)