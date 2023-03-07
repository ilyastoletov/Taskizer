package com.appninjas.taskizer.presentation.application

import android.app.Application
import com.appninjas.taskizer.presentation.di.appModule
import com.appninjas.taskizer.presentation.di.databaseModule
import com.appninjas.taskizer.presentation.di.domainModule
import com.appninjas.taskizer.presentation.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class Application: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@Application)
            modules(listOf(
                databaseModule,
                networkModule,
                domainModule,
                appModule
            ))
        }
    }

}