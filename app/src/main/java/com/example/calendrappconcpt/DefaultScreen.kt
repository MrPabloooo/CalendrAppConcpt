package com.example.calendrappconcpt

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun DefaultScreen(
    viewModel: CalendarViewModel,
    onCalendarClick: () -> Unit,
    onNoteClick: () -> Unit
) {


    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
    ) {



    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Button(
            onClick = { onCalendarClick() },
            modifier = Modifier
        ) {
            Text(text = "Go to Calendar")
        }

        Button(
            onClick = { onNoteClick() },
            modifier = Modifier
        ) {
            Text(text = "Add a note")
        }

    }



        IconButton(
            onClick = { onNoteClick() },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .background(Color.Yellow, shape = CircleShape)
                .size(75.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Info",
                modifier = Modifier.size(1000.dp)
            )
        }
}



}