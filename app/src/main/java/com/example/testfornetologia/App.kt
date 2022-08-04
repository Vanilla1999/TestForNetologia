package com.example.testfornetologia

import android.app.Application
import com.example.testfornetologia.di.ApplicationComponent
import com.example.testfornetologia.di.DaggerApplicationComponent

class App() : Application() {
    override fun onCreate() {
        appComponentMain = DaggerApplicationComponent.factory().create()
        super.onCreate()
    }
    companion object {
        lateinit var appComponentMain: ApplicationComponent
    }
}