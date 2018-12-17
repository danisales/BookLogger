package br.ufpe.cin.dso.booklogger

import android.app.Application
import com.squareup.leakcanary.LeakCanary

class BookLoggerApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return
        }
        LeakCanary.install(this)
    }
}

