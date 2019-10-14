package com.example.message

import com.example.account.Account
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.anyString
import org.mockito.BDDMockito.atLeastOnce
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.verify
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toFlux
import reactor.kotlin.core.publisher.toMono
import reactor.kotlin.test.test

/**
 * Created by wonwoo on 2016. 10. 27..
 */
@ExtendWith(MockitoExtension::class)
class MessageServiceTest(@Mock val messageRepository: MessageRepository) {

    private lateinit var messageService: MessageService

    private val account = Account("wonwoo", "pw")

    @BeforeEach
    fun setup() {
        messageService = MessageService(messageRepository)
    }

    @Test
    fun findAll() {
        val messages = listOf(Message(message = "test message", account = account)).toFlux()
        given(messageRepository.findAll()).willReturn(messages)
        val findAllMessages = messageService.findAll()

        findAllMessages.test().assertNext {

            assertThat(it.message).isEqualTo("test message")

        }.verifyComplete()

    }

    @Test
    fun save() {
        val message = Message(message = "test message", account = account)
        given(messageRepository.save(message)).willReturn(message.toMono())
        val saveMessage = messageService.save(message)

        saveMessage.test().assertNext {

            assertThat(it.message).isEqualTo("test message")

        }.verifyComplete()

    }


    @Test
    fun delete() {

        given(messageRepository.deleteById(anyString())).willReturn(Mono.empty())

        val delete = messageService.delete("test")

        delete.test().then {

            verify(messageRepository, atLeastOnce()).deleteById("test")

        }.verifyComplete()
    }
}