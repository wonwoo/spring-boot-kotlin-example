package com.example.message

import com.example.account.Account
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.stereotype.Controller
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@Controller
@RequestMapping("/message")
class MessageController(private val messageService: MessageService) {

    @ModelAttribute("messages")
    fun messages(): List<Message> = messageService.findAll()

    @ModelAttribute
    fun account(@AuthenticationPrincipal account: Account?): Account? = account

    @GetMapping
    fun findAll(): String {
        return "message"
    }

    @PostMapping
    fun save(@Valid @ModelAttribute messageForm: MessageForm, bindingResult: BindingResult,
             @AuthenticationPrincipal account: Account): String {
        if (bindingResult.hasErrors()) {
            return "message"
        }
        messageService.save(messageForm, account)
        return "redirect:/message"
    }

    @GetMapping("/delete/{id}")
    fun delete(@PathVariable id: Long): String {
        messageService.delete(id)
        return "redirect:/message"
    }
}