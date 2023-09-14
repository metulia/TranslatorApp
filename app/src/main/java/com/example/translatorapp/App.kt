package com.example.translatorapp

import android.app.Application
import com.example.translatorapp.di.application
import com.example.translatorapp.di.historyScreen
import com.example.translatorapp.di.mainScreen
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(applicationContext)
            modules(listOf(application, mainScreen, historyScreen))
        }
    }
}