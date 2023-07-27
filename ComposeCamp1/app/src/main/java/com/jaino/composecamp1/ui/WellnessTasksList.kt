package com.jaino.composecamp1.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

// mockk
fun getWellnessTasks() = List(30){ i -> WellnessTask(i, "Task # $i")}

@Composable
fun WellnessTasksList(
    modifier: Modifier = Modifier,
    onCloseTask: (WellnessTask) -> Unit,
    onCheckedTask: (WellnessTask, Boolean) -> Unit,
    list: List<WellnessTask> = remember { getWellnessTasks() }
){
    LazyColumn(
        modifier = modifier
    ){
        items(
            items = list,
            key = { task -> task.id }
        ){task ->
            WellnessTaskItem(
                taskName = task.label,
                onClose = { onCloseTask(task) },
                checked = task.checked.value,
                onCheckedChange = { checked -> onCheckedTask(task, checked) })
        }
    }
}