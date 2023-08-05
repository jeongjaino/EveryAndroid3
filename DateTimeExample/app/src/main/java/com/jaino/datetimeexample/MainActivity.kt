package com.jaino.datetimeexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jaino.datetimeexample.ui.theme.DateTimeExampleTheme
import java.time.Duration
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import kotlin.time.Duration.Companion.nanoseconds

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DateTimeExampleTheme {
                val date = remember {
                    // LocalDate.now()
                        // .dayOfMonth
                        // .dayOfWeek

                        // .dayOfYear
                        // .plusDays(30)
                    /*LocalTime.now() //
                        // .hour
                        // .plusHours(30)*/
                    // LocalDateTime.of(2022, 11, 1, 20, 0, 0)
                    // LocalDateTime.parse()
                    // LocalDate -> 생일, LocalTime -> 알람, 리마인더, LocalDateTime -> Calendar
                    // TimeZone -> 다른 나라의 사람과 일정을 잡는 앱 -> 다른 LocalTime
                    /*ZonedDateTime.now().toEpochSecond() // timeStamp
                    ZonedDateTime.ofInstant(
                        Instant.ofEpochMilli(System.currentTimeMillis()),
                        // ZoneId.systemDefault()
                        ZoneId.of("Asia/Seoul")
                    )*/
                    LocalDateTime.now()

                }
                val formattedDateTime = DateTimeFormatter
                    .ofPattern("yyyy/MM/EEE dd/HH/mm/ss")
                    .format(date)
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ){
                    Text(text = formattedDateTime)
                }
            }
        }
    }
}
