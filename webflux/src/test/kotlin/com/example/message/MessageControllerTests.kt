package com.example.message

import com.example.MockUser
import com.example.account.Account
import com.example.any
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.anyString
import org.mockito.BDDMockito.given
import org.mockito.Mockito.atLeastOnce
import org.mockito.Mockito.verify
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBody
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toFlux
import reactor.kotlin.core.publisher.toMono

@WebFluxTest(MessageController::class)
@MockUser
internal class MessageControllerTests(@Autowired private val webTestClient: WebTestClient) {

    @MockBean
    private lateinit var messageService: MessageService


    @Test
    internal fun `message all test`() {

        given(messageService.findAll()).willReturn(
            listOf(Message(id = "foo", message = "test message", account = Account("wonwoo", "foo"))).toFlux()
        )

        webTestClient.get()
            .uri("/message")
            .accept(MediaType.TEXT_HTML)
            .exchange()
            .expectStatus()
            .isOk
            .expectBody<String>()
            .consumeWith {
                assertThat(it.status).isEqualTo(HttpStatus.OK)
                assertThat(it.responseBody).contains("test message")
                assertThat(it.responseBody).contains("wonwoo")
                assertThat(it.responseBody).contains("just now")
            }

    }

    @Test
    internal fun `message save test`() {

        val message = Message(message = "test message", account = Account(id = "bar", name = "user", passwd = "password"))
        given(messageService.findAll()).willReturn(
            listOf(Message(id = "foo", message = "test message", account = Account("wonwoo", "foo"))).toFlux()
        )
        given(messageService.save(message)).willReturn(message.toMono())


        webTestClient.mutateWith(csrf())
            .post()
            .uri {
                it.queryParam("message", "test messsage")
                    .path("/message")
                    .build()
            }

            .accept(MediaType.TEXT_HTML)
            .exchange()
            .expectStatus()
            .isSeeOther
            .expectBody<String>()
            .consumeWith {

                verify(messageService, atLeastOnce()).save(any())
                verify(messageService, atLeastOnce()).findAll()

            }

    }

    @Test
    internal fun `message delete test`() {

        given(messageService.delete(anyString())).willReturn(Mono.empty())
        given(messageService.findAll()).willReturn(
            listOf(Message(id = "foo", message = "test message", account = Account("wonwoo", "foo"))).toFlux()
        )

        webTestClient.mutateWith(csrf())
            .post()
            .uri("/message/delete/{id}", "foo")

            .accept(MediaType.TEXT_HTML)
            .exchange()
            .expectStatus()
            .isSeeOther
            .expectBody<String>()
            .consumeWith {

                verify(messageService, atLeastOnce()).delete("foo")
                verify(messageService, atLeastOnce()).findAll()

            }
    }

}

