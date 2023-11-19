package com.wap.myapplication.side_effect

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect

@Composable
fun SideEffectExample(nonComposeStateCount: Int) {
    SideEffect {
        println("Recomposition이 성공했을 때 매번 호출된다.")
    }
}