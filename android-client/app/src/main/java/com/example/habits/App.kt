package com.example.habits

import android.app.Application
import com.example.habits.data.di.DataModule
import com.example.habits.domain.di.DomainModule
import com.example.habits.presentation.di.PresentationModule
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
