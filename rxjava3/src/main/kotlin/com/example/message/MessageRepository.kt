package com.example.message

import org.springframework.data.repository.reactive.RxJava3CrudRepository

interface MessageRepository : RxJava3CrudRepository<Message, String>