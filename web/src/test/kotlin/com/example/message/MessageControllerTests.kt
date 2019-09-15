package com.example.message

import com.example.MockUser
import com.example.account.Account
import org.hamcrest.CoreMatchers.containsString
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.doNothing
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.verify
import org.mockito.Mockito
import org.mockito.Mockito.atLeastOnce
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post

inline fun <reified T> any(): T = Mockito.any()

@WebMvcTest(MessageController::class)
@MockUser
internal class MessageControllerTests(@Autowired private val mockMvc: MockMvc) {

    @MockBean
    private lateinit var messageService: MessageService


    @Test
    internal fun `message all test`() {

        given(messageService.findAll()).willReturn(listOf(Message(id = 1L, message = "test message", account = Account("wonwoo", "foo"))))

        mockMvc.get("/message") {

            accept = MediaType.TEXT_HTML

        }.andExpect {
            status { isOk }
            model {
                attributeExists("messages")
            }
            view {
                name("message")
            }
            content {
                string(containsString("test message"))
            }
        }.andDo {
            print()
        }
    }

    @Test
    internal fun `message save test`() {

        val message = Message(message = "test message", account = Account(id = 1, name = "user", passwd = "password"))

        given(messageService.save(message)).willReturn(message)

        mockMvc.post("/message") {

            accept = MediaType.TEXT_HTML
            with(csrf())
            param("message", "test messsage")

        }.andExpect {
            status { isFound }
        }.andDo {
            print()
        }

        verify(messageService, atLeastOnce()).save(any())

    }


    @Test
    internal fun `message delete test`() {

        doNothing().`when`(messageService).delete(1L)

        mockMvc.post("/message/delete/{id}", 1) {

            accept = MediaType.TEXT_HTML
            with(csrf())

        }.andExpect {
            status { isFound }
        }.andDo {
            print()
        }

        verify(messageService, atLeastOnce()).delete(1L)

    }

}

