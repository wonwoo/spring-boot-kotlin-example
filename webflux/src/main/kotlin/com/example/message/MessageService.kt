package com.example.message

import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class MessageService(private val messageRepository: MessageRepository) {

    fun findAll(): Flux<Message> = messageRepository.findAll()

    fun save(message: Message): Mono<Message> = messageRepository.save(message)

    fun delete(id: String): Mono<Void> = messageRepository.deleteById(id)
}