package com.plcoding.testingcourse.part4.presentation

import com.plcoding.testingcourse.part1.data.FirebaseAnalyticsLogger
import com.plcoding.testingcourse.part1.domain.AnalyticsLogger
import com.plcoding.testingcourse.part1.domain.LogParam
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class GoodProfileViewModelTest {
    private lateinit var viewModel: GoodProfileViewModel

    @BeforeEach
    fun setUp() {
        viewModel = GoodProfileViewModel(
            analytics = object: AnalyticsLogger {
                override fun logEvent(key: String, vararg params: LogParam<Any>) = Unit
            }
        )
    }
}