package com.wap.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.wap.myapplication.ui.theme.MyApplicationTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var text by remember {
                mutableStateOf("")
            }
            MyApplicationTheme {
                LaunchedEffect(key1 = text) { // !! Coroutine Scope
                    delay(1000L) // !! 텍스트가 변경될 때마다, 취소되고 재개된다.
                    println("the text is $text") // !! 그럼 언제 출력될까?
                }
            }
        }
    }
}
