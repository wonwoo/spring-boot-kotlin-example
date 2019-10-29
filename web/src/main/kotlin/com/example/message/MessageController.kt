package com.example.message

import com.example.account.Account
import com.example.config.service.UserDetailsServiceImpl
import com.example.config.service.UserDetailsServiceImpl.CustomUserDetails
import com.example.formatDateAgo
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.stereotype.Controller
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@Controller
@RequestMapping("/message")
class MessageController(private val messageService: MessageService) {

    @ModelAttribute("messages")
    fun messages() = messageService.findAll().map { it.toDto() }

    @ModelAttribute("account")
    fun account(@AuthenticationPrincipal account: CustomUserDetails) = account

    @GetMapping
    fun findAll() = "message"

    @PostMapping
    fun save(@Valid @ModelAttribute messageForm: MessageForm, bindingResult: BindingResult,
             @ModelAttribute("account") account: CustomUserDetails): String {

        if (bindingResult.hasErrors()) {

            return "message"

        }

        messageService.save(messageForm.toMessage(account))

        return "redirect:/message"

    }

    @PostMapping("/delete/{id}")
    fun delete(@PathVariable id: Long): String {

        messageService.delete(id)

        return "redirect:/message"

    }
}

fun MessageForm.toMessage(account: CustomUserDetails) = Message(this.message, account.account)

fun Message.toDto() = MessageDto(this.message, this.account, this.regDate.formatDateAgo(), id)

data class MessageDto(

    val message: String,

    val account: Account,

    val regDate: String,

    val id: Long?
)