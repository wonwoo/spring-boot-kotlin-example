package com.example.message

import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface MessageRepository : ReactiveCrudRepository<Message, String>