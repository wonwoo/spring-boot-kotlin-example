package com.example

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono

//@SpringBootTest
class Rxjava2ApplicationTests {

    @Test
    fun contextLoads() {

//        Mono.just("!11")
//            .map {
//                throw NullPointerException()
//            }

//        Mono.just("a")
        Flux.just(1,2,3).take(1)
            .doOnCancel {
                println("doOnCancel")
            }

            .doAfterTerminate {
                println("doAfterTerminate")

            }
            .doOnComplete {
                println("doOnSuccess")
            }
            .doOnTerminate {
                println("doOnTerminate")
            }
            .doFinally {
                println(
                    "final"
                )
            }
            .subscribe()


    }

}
