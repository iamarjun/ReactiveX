package com.example.reactivex

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //

        Observable
            .fromIterable(DataSource.createTasksList())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Task> {
                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: Task) {
                    Log.d(TAG, "onNext: ${t.description}")
                }

                override fun onError(e: Throwable) {
                }

            })


        /*
        The Interval operator returns an Observable that emits an infinite sequence of ascending integers,
        with a constant interval of time of your choosing between emissions.
        I'm using the takeWhile() operator to perform a check to each emitted value.
        If the value becomes greater than 5, the observable stops emitting results.
         */

        Observable
            .interval(1, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
            .takeWhile {
                return@takeWhile it <= 10
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Long> {
                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: Long) {
                    Log.d(TAG, "onNext: $t")
                }

                override fun onError(e: Throwable) {
                }

            })


        Observable
            .timer(3, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object: Observer<Long> {
                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: Long) {
                    Log.d(TAG, "timer: $t")
                }

                override fun onError(e: Throwable) {
                }

            })

    }

    companion object {

        private val TAG = MainActivity::class.java.canonicalName
    }
}
