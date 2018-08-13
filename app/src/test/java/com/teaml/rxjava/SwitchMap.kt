package com.teaml.rxjava

import io.reactivex.Observable
import io.reactivex.schedulers.TestScheduler
import org.junit.Test
import java.util.concurrent.TimeUnit

class SwitchMap {


    val items = mutableListOf("Erin", "Mikasa", "Jean", "Armin", "Hange")

    val scheduler1 = TestScheduler()
    val scheduler2 = TestScheduler()

    @Test
    @Throws(Exception::class)
    fun switchMap() {
        val ob1 = Observable.fromIterable(items)


        ob1.switchMap {
            println("in SwitchMap : $it")
            val delay = 5L
            Observable.just("$it 12")
                    .delay(delay, TimeUnit.SECONDS, scheduler1)

        }.subscribe {
            println(it)
        }

        items.add("levi")

        scheduler1.advanceTimeBy(1, TimeUnit.MINUTES)


        ob1.switchMap {
            println("in switchMap2 : $it")
            val delay = 10L
            Observable.just("$it 12")
                    .delay(delay, TimeUnit.SECONDS, scheduler2)

        }.subscribe {
            println(it)
        }

        scheduler2.advanceTimeBy(2, TimeUnit.MINUTES)
    }
}