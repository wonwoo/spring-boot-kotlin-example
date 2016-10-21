package com.example.message

import com.example.account.Account
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
open class MessageService constructor(val messageRepository: MessageRepository){

    open fun findAll() : List<Message> {
        return messageRepository.findAll()
    }

    open fun save(messageForm: MessageForm, account: Account?) : Message {
        return messageRepository.save(Message(messageForm.message, account))
    }

    open fun findByAccount(account: Account?): List<Message> {
        return messageRepository.findByAccount(account)
    }
}