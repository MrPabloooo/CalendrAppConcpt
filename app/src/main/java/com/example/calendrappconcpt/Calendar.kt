package com.example.calendrappconcpt

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import java.time.LocalDate


@Composable
fun CalendarForMonth(
    month: MonthData,
    onCalendarClick: () -> Unit,
    viewModel: CalendarViewModel
) {






    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable {
            onCalendarClick()
        }

        ) {





        Column(
            modifier = Modifier.wrapContentWidth()
                .verticalScroll(rememberScrollState())
        ) {


            for (weekStart in 1..month.days step 7) {


                Row(
                ) {
                    val endDay = minOf(weekStart + 7, month.days + 1)

                    for (day in weekStart until minOf(weekStart + 7, month.days + 1)) {

                        if(LocalDate.now().dayOfMonth == day && LocalDate.now().monthValue == month.month && LocalDate.now().year == month.year) {
                            Box(
                                modifier = Modifier
                                    .padding(4.dp)
//                            .size(60.dp)
                                    .weight(1f)
                                    .aspectRatio(1f)
                                    .background(
                                        color =
                                            if (LocalDate.now().dayOfMonth == day && LocalDate.now().monthValue == month.month && LocalDate.now().year == month.year) {
                                                Color.Red
                                            } else {

                                                Color.Blue

                                            },
                                        shape = if (LocalDate.now().dayOfMonth == day && LocalDate.now().monthValue == month.month && LocalDate.now().year == month.year) {
                                            CircleShape
                                        } else {

                                            RoundedCornerShape(25f)
                                        },
                                    ),

                                contentAlignment = Alignment.Center
                            ) {
//                            Text(text = day.toString())
                            }
                        }
                        else {
                            Box(
                                modifier = Modifier
                                    .padding(4.dp)
//                            .size(60.dp)
                                    .weight(1f)
                                    .aspectRatio(1f),

                                contentAlignment = Alignment.Center
                            ) {
                                val notesRan = viewModel.getRandomNoteForSpecDay(LocalDate.of(month.year, month.month, day)).collectAsState(
                                    initial = null
                                )


                                if (notesRan.value != null) {
                                    Box(
                                        modifier = Modifier
                                            .padding(4.dp)
//                            .size(60.dp)
//                                            .weight(1f)
                                            .aspectRatio(1f)
                                            .background(
                                                color =
                                                    if (LocalDate.now().dayOfMonth == day && LocalDate.now().monthValue == month.month && LocalDate.now().year == month.year) {
                                                        Color.Red
                                                    } else {

                                                        Color.Blue

                                                    },
                                                shape = if (LocalDate.now().dayOfMonth == day && LocalDate.now().monthValue == month.month && LocalDate.now().year == month.year) {
                                                    CircleShape
                                                } else {

                                                    RoundedCornerShape(25f)
                                                },
                                            ),

                                        contentAlignment = Alignment.Center
                                    ) {
                                        AsyncImage(
                                            model = "file:///android_asset/${notesRan.value!!.Icon.toSvgName()}",

                                            contentDescription = "Icon"
                                        )
                                    }
                                }
                                else {
                                    Box(
                                        modifier = Modifier
                                            .size(5.dp)
                                            .background(
                                                color = Color.Red,
                                                shape = RoundedCornerShape(100f)
                                            )
                                    ) { }
                                }



                            }
                        }


                    }

                    repeat(7 - (endDay - weekStart)) {
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .aspectRatio(1f)
                                .padding(4.dp)
                        )
                    }
                }
            }
        }

    }
}