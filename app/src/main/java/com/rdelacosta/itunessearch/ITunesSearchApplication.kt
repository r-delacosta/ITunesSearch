package com.rdelacosta.itunessearch

import android.app.Application
import com.rdelacosta.itunessearch.utils.ClickableDebugTree
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class ITunesSearchApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(ClickableDebugTree())

    }

}