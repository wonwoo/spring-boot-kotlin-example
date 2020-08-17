package com.example.message

import javax.validation.constraints.NotBlank

data class MessageForm(@get:NotBlank val message: String)