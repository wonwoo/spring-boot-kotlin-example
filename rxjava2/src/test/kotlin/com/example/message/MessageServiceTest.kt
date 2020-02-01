package com.example.message

import com.example.account.Account
import com.example.toSingle
import io.reactivex.Completable
import io.reactivex.rxkotlin.toFlowable
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.anyString
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito.atLeastOnce
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension


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
        val messages = listOf(Message(message = "test message", account = account)).toFlowable()
        given(messageRepository.findAll()).willReturn(messages)
        val findAllMessages = messageService.findAll()

        findAllMessages.test().assertValue {
            it.message == "test message"
        }

    }

    @Test
    fun save() {
        val message = Message(message = "test message", account = account)
        given(messageRepository.save(message)).willReturn(message.toSingle())
        val saveMessage = messageService.save(message)

        saveMessage.test().assertValue {

            it.message == "test message"

        }
    }

    @Test
    fun delete() {

        given(messageRepository.deleteById(anyString())).willReturn(Completable.complete())

        val delete = messageService.delete("test")
        delete.test().assertComplete()
        verify(messageRepository, atLeastOnce()).deleteById("test")
    }
}