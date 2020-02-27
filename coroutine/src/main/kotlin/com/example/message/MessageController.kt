package com.example.message

import com.example.account.Account
import com.example.config.service.ReactiveUserDetailsServiceImpl.CustomUserDetails
import com.example.formatDateAgo
import kotlinx.coroutines.flow.map
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.stereotype.Controller
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.reactive.result.view.Rendering
import javax.validation.Valid

@Controller
@RequestMapping("/message")
class MessageController(private val messageService: MessageService) {

    @ModelAttribute("messages")
    fun messages() = messageService.findAll().map { it.toDto() }

    @ModelAttribute("account")
    fun account(@AuthenticationPrincipal account: CustomUserDetails) = account

    @GetMapping
    fun findAll() = Rendering.view("message").build()

    @PostMapping
    suspend fun save(@Valid @ModelAttribute messageForm: MessageForm, bindingResult: BindingResult,
                     @ModelAttribute("account") account: CustomUserDetails): Rendering {

        if (bindingResult.hasErrors()) {

            return Rendering.view("message").build()

        }

        return Rendering.redirectTo("/message")
            .modelAttribute("message", messageService.save(messageForm.toMessage(account)))
            .build()

    }

    @PostMapping("/delete/{id}")
    suspend fun delete(@PathVariable id: String): Rendering {

        return Rendering.redirectTo("/message")
            .modelAttribute("message", messageService.delete(id))
            .build()


    }
}

fun MessageForm.toMessage(account: CustomUserDetails) = Message(this.message, account.account)

fun Message.toDto() = MessageDto(this.message, this.account, this.regDate.formatDateAgo(), id)

data class MessageDto(

    val message: String,

    val account: Account,

    val regDate: String,

    val id: String?
)