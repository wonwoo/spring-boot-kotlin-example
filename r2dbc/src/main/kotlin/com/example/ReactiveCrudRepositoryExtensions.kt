package com.example

import org.reactivestreams.Publisher
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Mono

fun <S : T, T, ID> ReactiveCrudRepository<T, ID>.save(entity: Publisher<S>): Mono<S> {
    return Mono.from<S>(entity).flatMap { this.save(it) }
}

fun <S : T, T, ID> ReactiveCrudRepository<T, ID>.delete(entity: Publisher<S>): Mono<Void> {
    return Mono.from<S>(entity).flatMap { this.delete(it) }
}