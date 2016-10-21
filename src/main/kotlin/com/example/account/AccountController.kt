
package com.example.account

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import javax.validation.Valid

@Controller
@RequestMapping("/account")
open class AccountController constructor(val accountService: AccountService){

    @GetMapping
    fun findAll(model : Model) : String {
        model.addAttribute("accounts" , accountService.findAll())
        return "account"
    }

    @PostMapping
    fun save(@Valid @ModelAttribute accountForm: AccountForm, bindingResult: BindingResult, model: Model) : String {
        if(bindingResult.hasErrors()){
            return "account"
        }
        accountService.save(accountForm)
        return "account"
    }
}