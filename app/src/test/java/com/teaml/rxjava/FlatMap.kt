package com.teaml.rxjava

import io.reactivex.Observable
import io.reactivex.schedulers.TestScheduler
import org.junit.Test
import java.util.*
import java.util.concurrent.TimeUnit

class FlatMap {

    val items = listOf("Erin", "Mikasa", "Jean", "Armin", "Hange")

    val scheduler = TestScheduler()

    @Test
    @Throws(Exception::class)
    fun flatMap() {
        Observable.fromIterable(items)
                .flatMap {
                    val delay = 5L
                    Observable.just("$it 12")
                            .delay(delay, TimeUnit.SECONDS, scheduler)

                }.doOnNext {
                    println(it)
                }.subscribe()

        scheduler.advanceTimeBy(1, TimeUnit.MINUTES)
    }

}