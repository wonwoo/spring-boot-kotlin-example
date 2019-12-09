package com.example.message

import org.springframework.data.repository.reactive.RxJava2CrudRepository

interface MessageRepository : RxJava2CrudRepository<Message, String>