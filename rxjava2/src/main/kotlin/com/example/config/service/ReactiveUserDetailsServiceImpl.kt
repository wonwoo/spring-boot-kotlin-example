package com.example.config.service

import com.example.account.Account
import com.example.account.AccountRepository
import com.example.account.UserNotFoundException
import io.reactivex.Maybe
import io.reactivex.MaybeObserver
import io.reactivex.disposables.Disposable
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.ReactiveUserDetailsService
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import reactor.core.CoreSubscriber
import reactor.core.publisher.Mono
import reactor.core.publisher.Operators.MonoSubscriber


@Service
class ReactiveUserDetailsServiceImpl(private val accountRepository: AccountRepository) : ReactiveUserDetailsService {

    override fun findByUsername(username: String): Mono<UserDetails> {

        return Mono.from(accountRepository.findByname(username)
            .map { CustomUserDetails(it) }
            .switchIfEmpty(Maybe.defer<CustomUserDetails> {
                Maybe.error(UserNotFoundException("not found user name : $username"))
            }).toMono()
        )
    }

    class CustomUserDetails(val account: Account) : UserDetails {

        override fun getPassword(): String = account.password

        private fun authorities() =

            mutableListOf(SimpleGrantedAuthority("ROLE_USER")).apply {

                if (account.username == "wonwoo") {

                    this.add(SimpleGrantedAuthority("ROLE_ADMIN"))

                }
            }

        override fun getAuthorities() = authorities()

        override fun getUsername(): String = account.username

        override fun isAccountNonExpired(): Boolean = true

        override fun isAccountNonLocked(): Boolean = true

        override fun isCredentialsNonExpired(): Boolean = true

        override fun isEnabled(): Boolean = true
    }

}


fun <T> Maybe<T>.toMono(): Mono<T> {
    return MaybeToMono(this)
}

internal class MaybeToMono<T>(private val source: Maybe<T>) : Mono<T>() {

    override fun subscribe(s: CoreSubscriber<in T>) {
        source.subscribe(MaybeToMonoObserver(s))
    }

    internal class MaybeToMonoObserver<T>(subscriber: CoreSubscriber<in T>) : MonoSubscriber<T, T>(subscriber), MaybeObserver<T> {

        private lateinit var disposable: Disposable

        override fun onSubscribe(d: Disposable) {
            this.disposable = d
            actual.onSubscribe(this)
        }

        override fun onSuccess(value: T) {
            complete(value)
        }

        override fun onError(t: Throwable) {
            super.onError(t)
        }

        override fun cancel() {
            super.cancel()
            disposable.dispose()
        }
    }
}