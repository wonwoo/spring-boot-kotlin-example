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

    private fun authorities(account: Account): MutableCollection<out GrantedAuthority>? {
        val authorities = mutableListOf<GrantedAuthority>()
        authorities.add(SimpleGrantedAuthority("ROLE_USER"))
        if (account.name.equals("wonwoo")) authorities.add(SimpleGrantedAuthority("ROLE_ADMIN"))
        return authorities
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority>? = authorities(this)
    override fun isEnabled(): Boolean = true

    override fun getPassword(): String = passwd
}
