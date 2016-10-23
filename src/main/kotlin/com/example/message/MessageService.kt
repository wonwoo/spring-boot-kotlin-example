package com.example.message

import com.example.account.Account
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
open class MessageService(val messageRepository: MessageRepository){

    @Transactional(readOnly = true)
    open fun findAll() : List<Message> {
        return messageRepository.findAll()
    }

    open fun save(messageForm: MessageForm, account: Account?) : Message {
        return messageRepository.save(Message(messageForm.message, account))
    }

    @Transactional(readOnly = true)
    open fun findByAccount(account: Account?): List<Message> {
        return messageRepository.findByAccount(account)
    }

    open fun delete(id: Long) {
        messageRepository.delete(id)
    }
}