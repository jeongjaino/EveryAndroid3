package com.jaino.logexample

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.orhanobut.logger.Logger
import timber.log.Timber
import java.io.IOException

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        printXmlWithLogger()
        printJsonWithLogger()
    }

    private fun printLog(){
        Log.d("LOG_DEBUG", "hello_Log")
        Log.v("LOG_VERBOSE", "hello_Log")
        Log.wtf("LOG_WTF", "hello_Log")
        Log.e("LOG_ERROR", "hello_Log", IOException())
    }

    private fun printTimber(){
        Timber.v("Hello, Timber")
        Timber.tag("TIMBER_TAG").d("Hello, Timber")
        Timber.wtf("Hello, Timber")
        Timber.e("Hello, Timber", IOException())
    }

    private fun printLogger(){
        Logger.v("Hello, Logger")
        Logger.t("LOGGER_TAG").d("Hello, Logger")
        Logger.wtf("Hello, Logger")
        Logger.e("Hello, Logger", IOException())
    }

    private fun printXmlWithLogger(){
        Logger.xml("<사용자><이메일>user@example.com</이메일><이름>홍길동</이름><나이>30</나이></사용자>")
        Log.d("LOG_XML", "<사용자><이메일>user@example.com</이메일><이름>홍길동</이름><나이>30</나이></사용자>")
    }

    private fun printJsonWithLogger(){
        Logger.json("{" +
                "  \"이메일\": \"user@example.com\"," +
                "  \"이름\": \"홍길동\"," +
                "  \"나이\": 30" +
                "}")
        Log.d("LOG_JSON","{" +
                "  \"이메일\": \"user@example.com\"," +
                "  \"이름\": \"홍길동\"," +
                "  \"나이\": 30" +
                "}")
    }
}