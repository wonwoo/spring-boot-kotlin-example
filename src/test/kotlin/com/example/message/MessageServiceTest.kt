package com.example.message

import com.example.account.Account
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.anyLong
import org.mockito.BDDMockito.doNothing
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension

/**
 * Created by wonwoo on 2016. 10. 27..
 */
@ExtendWith(MockitoExtension::class)
class MessageServiceTest(@Mock val messageRepository: MessageRepository) {

    private lateinit var messageService: MessageService

    val account = Account("wonwoo", "pw")

    @BeforeEach
    fun setup() {
        messageService = MessageService(messageRepository)
    }

    @Test
    fun findAll() {
        val messages = listOf(Message(message = "test message", account = account))
        given(messageRepository.findAll()).willReturn(messages)
        val findAllMessages = messageService.findAll()
        assertThat(findAllMessages[0].message).isEqualTo("test message")
    }

    @Test
    fun save() {
        val message = Message(message = "test message", account = account)
        given(messageRepository.save(message)).willReturn(message)
        val saveMessage = messageService.save(message)
        assertThat(saveMessage.message).isEqualTo("test message")
    }


    @Test
    fun delete() {
        doNothing().`when`(messageRepository).deleteById(anyLong())
        messageService.delete(1)
    }
}