package com.example.todo

import android.app.Application
import com.facebook.stetho.Stetho

class TodoApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
    }
}