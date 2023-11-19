package com.wap.myapplication.derived_state

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun DerivedStateExample() {
    var count by remember {
        mutableStateOf(0)
    }
    val counterText by derivedStateOf { "The counter is $count" }
    Button(onClick = { count ++ }) {
        Text(text = counterText)
    }
}