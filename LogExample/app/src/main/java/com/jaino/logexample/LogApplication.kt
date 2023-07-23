package com.jaino.logexample

import android.app.Application
import android.util.Log
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.FormatStrategy
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import timber.log.Timber


class LogApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initialLogger()
    }

    private fun initialTimber(){
        if(BuildConfig.DEBUG){
            plantDebugTimberTree()
        }
        else{
            plantReleaseTimberTree()
        }
    }

    private fun plantDebugTimberTree(){
        Timber.plant(object : Timber.DebugTree() {
            override fun createStackElementTag(element: StackTraceElement): String? {
                return String.format(
                    "Class:%s: Line: %s, Method: %s",
                    super.createStackElementTag(element),
                    element.lineNumber,
                    element.methodName
                )
            }
        })
    }

    private fun plantReleaseTimberTree(){
        Timber.plant(ReleaseTimberTree())
    }

    // A tree which logs important information for crash reporting
    class ReleaseTimberTree : Timber.Tree() {
        override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
            if(priority == Log.VERBOSE || priority == Log.DEBUG){
                return
            }

            // FakeCrashLibrary.log(priority, tag, message)

            if(t != null){
                if(priority == Log.ERROR){
                    // FakeCrashLibrary.logError(priority, tag, message)
                }
                else if(priority == Log.WARN){
                    // FakeCrashLibrary.logWarning(priority, tag, message)
                }
            }
        }
    }

    private fun initialLogger(){
        Logger.addLogAdapter(object : AndroidLogAdapter() {
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return BuildConfig.DEBUG
            }
        })
    }
}