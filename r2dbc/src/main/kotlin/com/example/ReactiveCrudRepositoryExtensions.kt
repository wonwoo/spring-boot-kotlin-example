package com.example

import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Mono

fun <S : T, T, ID> ReactiveCrudRepository<T, ID>.save(entity: Mono<S?>): Mono<S> {
    return Mono.from<S>(entity).flatMap { this.save(it) }
}

fun <S : T, T, ID> ReactiveCrudRepository<T, ID>.delete(entity: Mono<S>): Mono<Void> {
    return Mono.from<S>(entity).flatMap { this.delete(it) }
}