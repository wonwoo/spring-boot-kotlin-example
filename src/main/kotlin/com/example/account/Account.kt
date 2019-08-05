package com.example.account

import com.example.message.Message
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.io.Serializable
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity
data class Account(

    val name: String,

    val passwd: String,

    @OneToMany(mappedBy = "account")
    val messages: List<Message>? = null,

    @Id @GeneratedValue
    val id: Long? = null

) : Serializable, UserDetails {

    override fun getUsername(): String = name

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    private fun authorities(account: Account): Collection<GrantedAuthority> {

        val authorities = mutableListOf(SimpleGrantedAuthority("ROLE_USER"))

        return authorities.apply {

            if (account.name == "wonwoo") {

                this.add(SimpleGrantedAuthority("ROLE_ADMIN"))

            }
        }
    }

    override fun getAuthorities(): Collection<GrantedAuthority> = authorities(this)

    override fun isEnabled(): Boolean = true

    override fun getPassword(): String = passwd
}
