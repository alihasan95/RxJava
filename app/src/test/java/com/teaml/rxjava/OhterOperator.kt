package com.teaml.rxjava

import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import org.junit.Test
import java.util.concurrent.TimeUnit

class OhterOperator {

    val observable = Observable.just(1, 2, 3, 4, 5)


    @Test
    @Throws(Exception::class)
    fun bufferOperator() {
        // one parameter : take n item each time and create bundle list
        // two parameter : jump  step each time
        observable.buffer(2, 1)
                .subscribe {
                    println(it)
                }

    }

    @Test
    @Throws(Exception::class)
    fun takeOperator() {
        //  emit only the first n items emitted by an Observable
        observable.take(3)
                .subscribe {
                    println(it)
                }
    }

    @Test
    fun reduceOperator() {
        // apply function on each item emit by observable
        observable.reduce { num1, num2 ->
            println("n1 : $num1, n2 : $num2")
            num1 + num2
        }.subscribe {
            println(it)
        }
    }

    @Test
    fun filterOperator() {
        //  emit only those items from an Observable that pass a predicate test
        observable.filter {
            it % 2 == 0
        }.subscribe {
            println(it)
        }
    }

    @Test
    fun skipOperator() {
        // skip the first n items emitted by an Observable
        observable.skip(2)
                .subscribe {
                    println(it)
                }
    }

    @Test
    fun concatOperator() {
        // emit the emissions from two or more Observables without interleaving them

        val ob2 = Observable.just(5, 6, 7, 8, 9 )

        Observable.concat(observable, ob2)
                .subscribe {
                    println(it)
                }
    }

    class Car() {
        var brand: String = "brand"
        fun getObservable() = Observable.defer {
            Observable.just(brand)
        }
    }
    @Test
    fun  defer() {
        // Even if we are setting the brand after creating Observable
        // we will get the brand as BMW.
        // If we had not used defer, we would have got null as the brand.

        val car = Car()
        val deferring = car.getObservable()
        car.brand = "BMW"

        deferring.subscribe {
            println(it)
        }

    }

}