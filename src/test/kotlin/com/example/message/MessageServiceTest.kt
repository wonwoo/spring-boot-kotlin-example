package com.example.message

import com.example.account.Account
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.*
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by wonwoo on 2016. 10. 27..
 */
@RunWith(MockitoJUnitRunner::class)
class MessageServiceTest {

    @Mock
    private lateinit var messageRepository: MessageRepository

    private lateinit var messageService: MessageService

    val account = Account("wonwoo", "pw")

    @Before
    fun setup() {
        messageService = MessageService(messageRepository)
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
        doNothing().`when`(messageRepository).deleteById(anyLong())
        messageService.delete(1)
    }
}