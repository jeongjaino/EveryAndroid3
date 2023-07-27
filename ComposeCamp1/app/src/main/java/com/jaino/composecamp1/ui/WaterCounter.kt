package com.jaino.composecamp1.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun WaterCounter(modifier: Modifier = Modifier){
    Column(modifier = modifier.padding(16.dp)) {
        var count by rememberSaveable{ mutableStateOf(0) }

        if(count > 0){
            Text(text = "$count glass")
        }
        Button(onClick = { count++ }, Modifier.padding(top = 8.dp), enabled = count < 10){
            Text("Add one")
        }
    }
}

@Composable
fun StatelessCounter(count: Int, onIncrement: () -> Unit, modifier: Modifier = Modifier){
    Column(modifier = modifier.padding(16.dp)) {
        if(count > 0){
            Text(text = "$count glass")
        }
        Button(onClick = onIncrement, Modifier.padding(top = 8.dp), enabled = count < 10){
            Text("Add one")
        }
    }
}

@Composable
fun StatefulCounter(){
    var waterCount by remember { mutableStateOf(0) }
    var juiceCount by remember { mutableStateOf(0) }

    StatelessCounter(waterCount, { waterCount++ })
    StatelessCounter(juiceCount, { juiceCount++ })
}