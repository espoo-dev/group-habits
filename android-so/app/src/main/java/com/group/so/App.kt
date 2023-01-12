package com.group.so

import android.app.Application
import com.group.so.data.di.DataModule
import com.group.so.domain.di.DomainModule
import com.group.so.presentation.di.PresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            DataModule.load()
            DomainModule.load()
            PresentationModule.load()
        }
    }
}
