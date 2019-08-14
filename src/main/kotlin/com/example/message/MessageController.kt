package com.example.message

import com.example.account.Account
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
    fun messages(): List<MessageDto> = messageService.findAll().map { it.toDto() }

    @ModelAttribute
    fun account(@AuthenticationPrincipal account: Account): Account = account

    @GetMapping
    fun findAll() = "message"

    @PostMapping
    fun save(@Valid @ModelAttribute messageForm: MessageForm, bindingResult: BindingResult, account: Account): String {

        if (bindingResult.hasErrors()) {

            return "message"

        }

        messageService.save(messageForm.toMessage(account))

        return "redirect:/message"

    }

    //TODO GET -> POST
    @GetMapping("/delete/{id}")
    fun delete(@PathVariable id: Long): String {

        messageService.delete(id)

        return "redirect:/message"

    }
}

fun MessageForm.toMessage(account: Account) = Message(this.message, account)

fun Message.toDto() = MessageDto(this.message, this.account, this.regDate.formatDateAgo(), id)

data class MessageDto(

    val message: String,

    val account: Account,

    val regDate: String,

    val id: Long?
)