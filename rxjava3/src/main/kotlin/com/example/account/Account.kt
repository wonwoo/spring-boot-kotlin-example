package com.example.account

import com.example.message.Message
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.io.Serializable

data class Account(

    val name: String,

    val passwd: String,

    @DBRef
    val messages: List<Message>? = null,

    @Id
    val id: String? = null

) : Serializable, UserDetails {

    override fun getUsername() = name

    override fun isCredentialsNonExpired() = true

    override fun isAccountNonExpired() = true

    override fun isAccountNonLocked() = true

    private fun authorities(account: Account) =

        mutableListOf(SimpleGrantedAuthority("ROLE_USER")).apply {

            if (account.name == "wonwoo") {

                this.add(SimpleGrantedAuthority("ROLE_ADMIN"))

            }
        }

    override fun getAuthorities() = authorities(this)

    override fun isEnabled(): Boolean = true

    override fun getPassword(): String = passwd
}
