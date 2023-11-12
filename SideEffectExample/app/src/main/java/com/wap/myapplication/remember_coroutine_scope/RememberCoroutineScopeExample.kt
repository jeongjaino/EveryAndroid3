package com.wap.myapplication.remember_coroutine_scope

import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun RememberCoroutineScopeExample() {
    val scope = rememberCoroutineScope()
    scope.launch {

    }
    Button(
        onClick = { // !! Not Composition, Yes Callback
            scope.launch {
                delay(1000L)
                println("")
           }
        }
    ) { }
}