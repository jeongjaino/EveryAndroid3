package com.jaino.composecamp1.ui

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel

class WellnessViewModel: ViewModel() {

    private val _task = getWellnessTasks().toMutableStateList()
    val task: List<WellnessTask> get() = _task

    fun remove(task: WellnessTask){
        _task.remove(task)
    }

    fun changeTaskChecked(item: WellnessTask, checked: Boolean) =
        _task.find{ it.id == item.id }?.let{ task ->
            task.checked.value = checked
        }
}