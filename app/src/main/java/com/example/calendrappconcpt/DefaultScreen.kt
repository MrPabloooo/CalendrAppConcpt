package com.example.calendrappconcpt

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.compose.foundation.layout.width
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import kotlin.math.absoluteValue
import kotlin.random.Random






@Composable
fun StreakView(
    streak: CalendarViewModel.Streak?,
    viewModel: CalendarViewModel
) {

    val dynamicColor = if (isSystemInDarkTheme()) Color.White else Color.Black

    val context = LocalContext.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {

        Spacer(modifier = Modifier.weight(1f))

        val today = LocalDate.now()

        val notes by viewModel
            .getNotes(today)
            .collectAsState(initial = emptyList())

        val danger = notes.isEmpty()

        if (streak != null) {


        Box(

            modifier = Modifier
                .clickable
                    (
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                )
                     {
                        Toast.makeText(
                            context,
                            "Current Streak: ${streak.current}🔥 \nLongest Streak: ${streak.longest}🔥",
                            Toast.LENGTH_SHORT
                        ).show()

                    }
                )

        {
        Text(
            text = "🔥 ${streak.current}${if (danger && streak.current > 0) "!" else ""}",
            fontSize = 16.sp,
            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
            color = if (danger) Color.DarkGray else dynamicColor

        )
        }

        Spacer(modifier = Modifier.width(16.dp))

        }


//        Text(
//            text = "Najdłuższy: ${streak.longest}"
//        )
    }
}

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
            .verticalScroll(rememberScrollState()),
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

        /*
            Text(
                text = "Today is: ${todayDate.dayOfMonth}/${todayDate.monthValue}/${todayDate.year}",
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,

            )

         */

        val streak by viewModel.streak.collectAsState()

        LaunchedEffect(streak?.current) {
            streak?.let {
                StreakDataStore.saveStreak(
                    context,
                    it.current
                )
            }
        }

        StreakView(streak, viewModel)



        if (randomNote != null) {
                Box(
                    Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    CardScreen(
                        Title = randomNote!!.title,
                        Icon = randomNote!!.Icon,
                        Contents = randomNote!!.Contents,
                        AdditionalInfo = "${days?.absoluteValue} days ago\n${randomNote!!.date.dayOfMonth}/${randomNote!!.date.monthValue}/${randomNote!!.date.year}",
                        Date = randomNote!!.date.toString(),
                        OnRefresh = {
                          viewModel.getRandomById()
                        },
                        Refreshable = true,
                        color = randomNote!!.color.toColorPaterns()
                    )





                }





            }





        Spacer(Modifier.height(32.dp))


        Box(
            modifier = Modifier
                .fillMaxWidth()
//                .weight(1f)
        ) {
            CalendarForMonth(
                month = month,
                onCalendarClick = {
                    onCalendarClick()
                },
                viewModel = viewModel
            )


        }





        /*


        ///////// TEST UNIT FOR ADDING RANDOM NOTES (FOR TEST BUILDS) /////////
        Button(
            onClick = {


                    val endDate = LocalDate.now()
                    val startDate = endDate.minusMonths(2)

                    val randomDate = startDate.plusDays(
                        Random.nextLong(
                            ChronoUnit.DAYS.between(startDate, endDate) + 1
                        )
                    )




                for (i in 1..6) {
                    viewModel.addCalendarItem(
                        CalendarItem(
                            title = "Test note item",
                            Icon = NoteIcon.valueOf(
                                (NoteIcon.values()).random().name
                            ),
                            Contents = "Test ONLY note!!!",
                            date = LocalDate.of(2026, 9, i),
                            color = ColorsOfNotes.valueOf(
                                (ColorsOfNotes.values()).random().name
                            )

                        )
                    )
                }

                for (i in 1..31) {
                    viewModel.addCalendarItem(
                        CalendarItem(
                            title = "Test note item",
                            Icon = NoteIcon.valueOf(
                                (NoteIcon.values()).random().name
                            ),
                            Contents = "Test ONLY note!!!",
                            date = LocalDate.of(2026, 8, i),
                            color = ColorsOfNotes.valueOf(
                                (ColorsOfNotes.values()).random().name
                            )

                        )
                    )
                }


                for (i in 1..31) {
                    viewModel.addCalendarItem(
                        CalendarItem(
                            title = "Test note item",
                            Icon = NoteIcon.valueOf(
                                (NoteIcon.values()).random().name
                            ),
                            Contents = "Test ONLY note!!!",
                            date = LocalDate.of(2026, 7, i),
                            color = ColorsOfNotes.valueOf(
                                (ColorsOfNotes.values()).random().name
                            )

                        )
                    )
                }






                Toast.makeText(
                    context,
                    "Added random note",
                    Toast.LENGTH_SHORT
                ).show()
            },
        ) {
            Text(text = "AddRandom <-TEST--ONLY-> ")
        }



        val count by viewModel.calendarItemsCount.collectAsState(initial = 0)

        Text("$count")


/////////////////////////



         */


    }


        val todayDate = LocalDate.now()

        val notes by viewModel
            .getNotes(todayDate)
            .collectAsState(initial = emptyList())

        val canAddNewNote = notes.size < 999

        IconButton(
            onClick = {
                if (canAddNewNote) {
                    onNoteClick()
                } else {
                    Toast.makeText(
                        context,
                        "Daily note limit reached",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
                .background(Color(0xFFE5C415), shape = CircleShape)
                .size(60.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.rounded_add_24),
                tint = Color.White,
                contentDescription = "Adder",
                modifier = Modifier.size(40.dp)
            )
        }
}



}
