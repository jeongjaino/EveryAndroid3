package com.wap.myapplication.produce_state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.produceState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun produceStateExample(countUpTo: Int): State<Int> {
    return produceState(initialValue = 0) {
        while(value < countUpTo) {
            delay(1000L)
            value ++
        }
        val flow = MutableStateFlow<Int>(0)

        flow.collectAsState()
    }
}