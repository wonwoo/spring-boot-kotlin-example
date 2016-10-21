package com.example.account

import com.example.NULL
import com.example.message.Message
import org.hibernate.validator.constraints.NotEmpty
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.io.Serializable
import javax.persistence.*

@Entity
@Access(AccessType.FIELD)
data class Account(

        @get:NotEmpty
        var name: String? = NULL,

        @get:NotEmpty
        var passwd: String? = NULL,

        @OneToMany(mappedBy = "account")
        var messages: List<Message>? = NULL,

        @Id @GeneratedValue
        var id: Long? = NULL
) : Serializable, UserDetails {
    override fun getUsername(): String = name!!

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    fun authorities(account: Account): MutableCollection<out GrantedAuthority>? {
        val authorities = mutableListOf<GrantedAuthority>()
        authorities.add(SimpleGrantedAuthority("ROLE_USER"))
        if (account.name.equals("wonwoo")) authorities.add(SimpleGrantedAuthority("ROLE_ADMIN"))
        return authorities
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority>? = authorities(this)
    override fun isEnabled(): Boolean = true

    override fun getPassword(): String = passwd!!
}
