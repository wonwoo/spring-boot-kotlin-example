package com.example.message

import com.example.MockUser
import com.example.account.Account
import org.hamcrest.CoreMatchers.containsString
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.doNothing
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.verify
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.model
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.view

inline fun <reified T> any(): T = Mockito.any()

@WebMvcTest(MessageController::class)
@MockUser
internal class MessageControllerTests(@Autowired private val mockMvc: MockMvc) {

    @MockBean
    private lateinit var messageService: MessageService


    @Test
    internal fun `message all test`() {

        given(messageService.findAll()).willReturn(listOf(Message(id = 1L, message = "test message", account = Account("wonwoo", "foo"))))

        mockMvc.perform(get("/message"))
            .andExpect(status().isOk)

            .andExpect(model().attributeExists("messages"))
            .andExpect(view().name("message"))
            .andExpect(content().string(containsString("test message")))
            .andDo(print())

    }

    @Test
    internal fun `message save test`() {

        val message = Message(message = "test message", account = Account(id = 1, name = "user", passwd = "password"))

        given(messageService.save(message)).willReturn(message)

        mockMvc.perform(post("/message")
            .with(csrf())
            .param("message", "test messsage"))
            .andDo(print())
            .andExpect(status().isFound)

        verify(messageService, atLeastOnce()).save(any())

    }



    @Test
    internal fun `message delete test`() {

        doNothing().`when`(messageService).delete(1L)

        mockMvc.perform(get("/message/delete/{id}", 1)
            .with(csrf()))
            .andDo(print())
            .andExpect(status().isFound)

        verify(messageService, atLeastOnce()).delete(1L)

    }

}


