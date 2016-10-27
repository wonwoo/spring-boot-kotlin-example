package com.example.message

import com.example.account.Account
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.runners.MockitoJUnitRunner
import org.junit.Assert.*
import org.junit.Before
import org.mockito.BDDMockito.*
import org.hamcrest.CoreMatchers.`is`

/**
 * Created by wonwoo on 2016. 10. 27..
 */
@RunWith(MockitoJUnitRunner::class)
class MessageServiceTest {

    @Mock
    lateinit var messageRepository: MessageRepository

    lateinit var messageService : MessageService

    val account = Account()

    @Before
    fun setup() {
        messageService = MessageService(messageRepository)
        account.id = 1
        account.name = "wonwoo"
    }

    @Test
    fun findAll() {
        val messages = listOf(Message(message = "test message", account = account))
        given(messageRepository.findAll()).willReturn(messages)
        val findAllMessages = messageService.findAll()
        assertThat(findAllMessages[0].message, `is`("test message"))
    }

    @Test
    fun save() {
        val message = Message(message = "test message", account = account)
        given(messageRepository.save(message)).willReturn(message)
        val messageForm = MessageForm("test message")
        val saveMessage = messageService.save(messageForm = messageForm, account = account)
        assertThat(saveMessage.message, `is`("test message"))
    }


    @Test
    fun delete() {
        doNothing().`when`(messageRepository).delete(anyLong())
        messageService.delete(1)
    }
}