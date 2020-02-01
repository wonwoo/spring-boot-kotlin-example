package com.example

import io.reactivex.Maybe
import io.reactivex.Single
import org.mockito.Mockito

inline fun <reified T> any(): T = Mockito.any()

fun <T : Any> T.toMaybe(): Maybe<T> = Maybe.just(this)

fun <T : Any> T.toSingle(): Single<T> = Single.just(this)
