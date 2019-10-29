package com.example.message

import com.example.account.Account
import com.example.config.service.ReactiveUserDetailsServiceImpl.CustomUserDetails
import com.example.formatDateAgo
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
    fun messages() = messageService.findAll()

    @ModelAttribute("account")
    fun account(@AuthenticationPrincipal account: CustomUserDetails) = account

    @GetMapping
    fun findAll() = Rendering.view("message").build()

    @PostMapping
    fun save(@Valid @ModelAttribute messageForm: MessageForm, bindingResult: BindingResult,
             @ModelAttribute("account") account: CustomUserDetails): Rendering {

        if (bindingResult.hasErrors()) {

            return Rendering.view("message").build()

        }

        return Rendering.redirectTo("/message")
            .modelAttribute("message", messageService.save(messageForm.toMessage(account)
                ?: throw IllegalArgumentException("must not be null!")))
            .build()

    }

    @PostMapping("/delete/{id}")
    fun delete(@PathVariable id: Long): Rendering {

        return Rendering.redirectTo("/message")
            .modelAttribute("message", messageService.delete(id))
            .build()


    }
}

fun MessageForm.toMessage(account: CustomUserDetails) = account.getId()?.let { Message(this.message, it) }

fun Message.toDto(account: Account) = MessageDto(this.message, account, this.regDate.formatDateAgo(), id)

data class MessageDto(

    val message: String,

    val account: Account,

    val regDate: String,

    val id: Long?
)