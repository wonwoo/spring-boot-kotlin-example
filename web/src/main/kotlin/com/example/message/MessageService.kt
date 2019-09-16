package com.example.message

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class MessageService(private val messageRepository: MessageRepository) {

    fun findAll(): List<Message> = messageRepository.findAll()

    @Transactional
    fun save(message: Message) = messageRepository.save(message)

    @Transactional
    fun delete(id: Long) = messageRepository.deleteById(id)
}