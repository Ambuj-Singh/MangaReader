package com.android.test.mangareader

import android.app.Application
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.android.test.mangareader.services.RestClient

class AssemblyContractorApplication : Application(), LifecycleEventObserver {

    override fun onCreate() {
        super.onCreate()
        RestClient.initRestConfig(this, BuildConfig.BASE_URL)

    }
    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {

    }
}