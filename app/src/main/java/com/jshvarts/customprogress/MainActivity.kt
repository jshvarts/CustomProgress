package com.jshvarts.customprogress

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private val itemCount = 100
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadingProgressBar.max = itemCount

        observeProgress()
                .concatMap { Observable.just(it).delay(50, TimeUnit.MILLISECONDS) }
                .subscribe ( { loadingProgressBar.progress++ }, Timber::e)
    }

    private fun observeProgress(): Observable<Int> {
        return Observable.range(1, itemCount)
    }
}