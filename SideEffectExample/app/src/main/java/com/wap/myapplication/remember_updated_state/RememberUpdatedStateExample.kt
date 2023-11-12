package com.wap.myapplication.remember_updated_state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import kotlinx.coroutines.delay

@Composable
fun RememberUpdatedStateExample(
    onTimeout: () -> Unit,
) {
    val updatedOnTimeout by rememberUpdatedState(newValue = onTimeout)
    LaunchedEffect(true) {
        delay(3000L)
        updatedOnTimeout()
    }
}