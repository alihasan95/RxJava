package com.teaml.rxjava

import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import org.junit.Test

class ZipOperator {

    data class Combine(var user: String, var love: String)

    val userList = listOf("user1", "user2")
    val loveList = listOf("anime1", "anime2")

    @Test
    @Throws(Exception::class)
    fun zipOperator() {

        val observableUser = Observable.fromArray(userList)
        val observableLove = Observable.fromArray(loveList)

        Observable.zip(observableUser, observableLove,
                BiFunction<List<String>, List<String>, List<Combine>> { t1, t2 ->
                    combineTowList()
                })
                .subscribe {
                    print(it)
                }

    }

    fun combineTowList(): MutableList<Combine> {
        val list = mutableListOf<Combine>()
        for (i in 0 until userList.size)
            list.add(Combine(userList[i], loveList[i]))
        return list
    }
    
}