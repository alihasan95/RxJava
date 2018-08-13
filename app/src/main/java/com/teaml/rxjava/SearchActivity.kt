package com.teaml.rxjava

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_search.*
import java.util.concurrent.TimeUnit

class SearchActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val publisher = PublishSubject.create<CharSequence>()

        // Observable .....
        editSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(p0 != null)
                    publisher.onNext(p0)
                else
                    publisher.onNext("")
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })

        // Observer .....
        publisher.debounce(300, TimeUnit.MILLISECONDS)
                .switchMap {
                    Observable.just(it).delay(2L, TimeUnit.SECONDS)
                }.subscribe(object : Observer<CharSequence> {
                    override fun onComplete() {
                        setLog("onComplete")
                    }

                    override fun onSubscribe(d: Disposable) {
                        setLog("onSubscribe")
                    }

                    override fun onNext(t: CharSequence) {
                        setLog("$t")
                    }

                    override fun onError(e: Throwable) {
                        setLog("onError : $e")
                    }

                 })
    }

    private fun setLog(text: String) {
        Log.e("main", text)
    }


}


