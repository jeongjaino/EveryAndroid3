package com.wap.myapplication.disposable_effect

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver

@Composable
fun DisposableEffectExample() {
    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(key1 =  ) {
        val observer = LifecycleEventObserver { _, event ->
            if(event == Lifecycle.Event.ON_PAUSE) {
                println("On pause called")
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
}