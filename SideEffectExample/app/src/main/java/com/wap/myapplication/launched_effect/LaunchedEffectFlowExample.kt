package com.wap.myapplication.launched_effect

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

@Composable
fun LaunchedEffectFlowExample(
    viewModel: LaunchedEffectViewModel
) {
    LaunchedEffect(key1 = true) { // !! true는 단 한번만 호출
        viewModel.sharedFlow.collect { event ->
            when(event) {
                is LaunchedEffectViewModel.ScreenEvents.Navigate -> { }

                is LaunchedEffectViewModel.ScreenEvents.ShowSnackbar -> { }
            }
        }
    }
}