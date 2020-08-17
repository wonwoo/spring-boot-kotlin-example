package com.example


import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import org.mockito.Mockito

inline fun <reified T> any(): T = Mockito.any()

fun <T : Any> T.toMaybe(): Maybe<T> = Maybe.just(this)

fun <T : Any> T.toSingle(): Single<T> = Single.just(this)
