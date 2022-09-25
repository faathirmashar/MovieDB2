package id.sharekom.moviedb

import android.app.Application
import id.sharekom.moviedb.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

import org.koin.core.logger.Level

class MyApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        // Start Koin
        startKoin{
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(appModule)
        }
    }
}