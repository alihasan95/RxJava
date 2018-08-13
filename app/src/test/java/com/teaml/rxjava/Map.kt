package com.teaml.rxjava

import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import org.junit.Test

class Map {

    data class User(var name: String)
    data class UserApi(var name: String)


    @Test
    @Throws(Exception::class)
    fun mapOperator() {

        getObservable().doOnNext {
            println(it.name)
        }.subscribe()
    }

    fun getObservable(): Observable<User> {

        val observable = Observable.just(UserApi("ali"))

        return observable.map {
            User(it.name)
        }
    }
}