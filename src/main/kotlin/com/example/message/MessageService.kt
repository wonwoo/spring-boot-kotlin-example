package com.example.message

import com.example.account.Account
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class MessageService(private val messageRepository: MessageRepository) {

    @Transactional(readOnly = true)
    fun findAll(): List<Message> = messageRepository.findAll()

    fun save(message: Message) = messageRepository.save(message)

    @Transactional(readOnly = true)
    fun findByAccount(account: Account): List<Message> = messageRepository.findByAccount(account)

    fun delete(id: Long) = messageRepository.deleteById(id)
}