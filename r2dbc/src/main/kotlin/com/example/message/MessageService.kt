package com.example.message

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
@Transactional(readOnly = true)
class MessageService(private val messageRepository: MessageRepository) {

    fun findAll(): Flux<Message> = messageRepository.findAll()

    @Transactional
    fun save(message: Message): Mono<Message> = messageRepository.save(message)

    @Transactional
    fun delete(id: String): Mono<Void> = messageRepository.deleteById(id)
}