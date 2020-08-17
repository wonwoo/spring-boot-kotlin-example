package com.example.message

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import org.springframework.stereotype.Service

@Service
class MessageService(private val messageRepository: MessageRepository) {

    fun findAll(): Flowable<Message> = messageRepository.findAll()

    fun save(message: Message): Single<Message> = messageRepository.save(message)

    fun delete(id: String): Completable = messageRepository.deleteById(id)
}