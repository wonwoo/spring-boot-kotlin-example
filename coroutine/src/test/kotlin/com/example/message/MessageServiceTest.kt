package com.example.message

import com.example.account.Account
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
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

/**
 * Created by wonwoo on 2016. 10. 27..
 */
@ExtendWith(MockitoExtension::class)
class MessageServiceTest(@Mock private val messageRepository: MessageRepository) {

    private lateinit var messageService: MessageService

    private val account = Account("wonwoo", "pw")

    @BeforeEach
    fun setup() {
        messageService = MessageService(messageRepository)
    }

    @Test
    fun findAll() {
        runBlocking {
            val messages = flowOf(Message(message = "test message", account = account))
            given(messageRepository.findAll()).willReturn(messages)
            val findAllMessages = messageService.findAll().toList()
            assertThat(findAllMessages[0].message).isEqualTo("test message")
        }
    }

    @Test
    fun save() {

        runBlocking {
            val message = Message(message = "test message", account = account)
            given(messageRepository.save(message)).willReturn(message)
            val saveMessage = messageService.save(message)
            assertThat(saveMessage.message).isEqualTo("test message")
        }
    }


    @Test
    fun delete() {


        runBlocking {
            given(messageRepository.deleteById(anyString())).willReturn(Unit)

            messageService.delete("test")

            verify(messageRepository, atLeastOnce()).deleteById("test")
        }
    }
}