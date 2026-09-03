package com.example.calendrappconcpt

import android.graphics.drawable.Icon
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import java.time.LocalDate
import java.time.temporal.ChronoUnit



@Composable
fun DefaultScreen(
    viewModel: CalendarViewModel,
    onCalendarClick: () -> Unit,
    onNoteClick: () -> Unit
) {

    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.getRandomById()
    }



    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
    ) {



    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        val todayDate = LocalDate.now()
        val month = MonthData(
            name = todayDate.month.toString(),
            month = todayDate.month.value,
            year = todayDate.year,
            days = todayDate.lengthOfMonth()
        )

        val notes = viewModel.getNotes(todayDate)

        val randomNote by viewModel.randomNote.collectAsState()

        val days = randomNote?.let {
            ChronoUnit.DAYS.between(todayDate, it.date)
        }

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Today is: ${todayDate.dayOfMonth}/${todayDate.monthValue}/${todayDate.year}",
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,

            )



            if (randomNote != null) {
                Box(
                    Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    CardScreen(
                        Title = randomNote!!.title,
                        Icon = randomNote!!.Icon,
                        Contents = randomNote!!.Contents,
                        AdditionalInfo = "$days days ago",
                        Date = randomNote!!.date.toString(),
                        OnRefresh = {
                          viewModel.getRandomById()
                        },
                        Refreshable = true
                    )





                }





            }







        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            CalendarForMonth(
                month = month,
                onCalendarClick = {
                    onCalendarClick()
                }
            )

        }






    }


        val todayDate = LocalDate.now()

        val notes by viewModel
            .getNotes(todayDate)
            .collectAsState(initial = emptyList())

        val canAddNewNote = notes.size <= 1

        IconButton(
            onClick = {
                if (canAddNewNote) {
                    onNoteClick()
                } else {
                    Toast.makeText(
                        context,
                        "Limit reached",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .background(Color.Yellow, shape = CircleShape)
                .size(75.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Adder"
            )
        }
}



}