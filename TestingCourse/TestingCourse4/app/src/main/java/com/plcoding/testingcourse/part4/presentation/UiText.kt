package com.plcoding.testingcourse.part4.presentation

import android.content.Context

sealed class UiText {
    data class DynamicText(val value: String): UiText()
    data class StringResource(
        val id: Int,
        val args: List<Any?> = emptyList()
    ): UiText()

    fun asString(context: Context): String{
        return when(this){
            is DynamicText -> value
            is StringResource -> context.getString(
                id,
                *args.toTypedArray()
            )
        }
    }
}