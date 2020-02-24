package com.example.message

import kotlinx.coroutines.flow.Flow
import org.springframework.stereotype.Service

@Service
class MessageService(private val messageRepository: MessageRepository) {

    fun findAll(): Flow<Message> = messageRepository.findAll()

    suspend fun save(message: Message): Message = messageRepository.save(message)

    suspend fun delete(id: String): Unit = messageRepository.deleteById(id)
}