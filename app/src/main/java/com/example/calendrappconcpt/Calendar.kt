package com.example.calendrappconcpt

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.error
import coil3.request.fallback
import coil3.request.placeholder
import java.time.LocalDate


@Composable
fun CalendarForMonth(
    month: MonthData,
    onCalendarClick: () -> Unit,
    viewModel: CalendarViewModel
) {
    val dynamicColor = if (isSystemInDarkTheme()) Color.White else Color.Black





    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable(
            indication = null,
            interactionSource = remember { MutableInteractionSource() }
        ) {
            onCalendarClick()
        }
    ) {

        Column(
            modifier = Modifier
                .wrapContentWidth()
                .verticalScroll(rememberScrollState())
        ) {

            for (weekStart in 1..month.days step 7) {

                Row {
                    val endDay = minOf(
                        weekStart + 7,
                        month.days + 1
                    )

                    for (day in weekStart until endDay) {

                        val date = LocalDate.of(
                            month.year,
                            month.month,
                            day
                        )

                        val notesRan = remember(date) {
                            viewModel.getRandomNoteForSpecDay(date)
                        }.collectAsState(initial = null)

                        if (
                            LocalDate.now().dayOfMonth == day &&
                            LocalDate.now().monthValue == month.month &&
                            LocalDate.now().year == month.year
                        ) {
                            Box(
                                modifier = Modifier
                                    .padding(4.dp)
                                    .weight(1f)
                                    .aspectRatio(1f)
                                    .border(
                                        width = 1.dp,
                                        color = dynamicColor,
                                        shape = CircleShape
                                    )
                                    .padding(8.dp),
                                contentAlignment = Alignment.Center
                            ) {
//                            Text(text = day.toString())

                                if (notesRan.value != null) {
                                    AsyncImage(
                                        model = "file:///android_asset/${notesRan.value?.Icon?.toSvgName()}",
                                        contentDescription = "IconTDY",
                                                colorFilter = ColorFilter.tint(

                                                color = Color.White
                                                ),
                                    )
                                }

                                if (notesRan.value == null) {
                                    Box(
                                        modifier = Modifier
                                            .size(10.dp)
                                            .background(
                                                color = dynamicColor,
                                                shape = RoundedCornerShape(100f)
                                            )
                                    ) { }
                                }
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



                                if (notesRan.value != null) {
                                    Box(
                                        modifier = Modifier
                                            .padding(4.dp)
//                            .size(60.dp)
//                                            .weight(1f)
                                            .aspectRatio(1f),
//                                            .background(
//                                                color =
//                                                    if (LocalDate.now().dayOfMonth == day && LocalDate.now().monthValue == month.month && LocalDate.now().year == month.year) {
//                                                        Color.Red
//                                                    } else {
//
//                                                        Color.Blue
//
//                                                    },
//                                                shape = if (LocalDate.now().dayOfMonth == day && LocalDate.now().monthValue == month.month && LocalDate.now().year == month.year) {
//                                                    CircleShape
//                                                } else {
//
//                                                    RoundedCornerShape(25f)
//                                                },
//                                            ),

                                        contentAlignment = Alignment.Center
                                    ) {
                                        AsyncImage(
                                            model = "file:///android_asset/${notesRan.value?.Icon?.toSvgName()}",






                                            contentDescription = "Icon",


                                                    colorFilter = ColorFilter.tint(
                                                        color = dynamicColor
                                                    ),
                                        )
                                    }
                                }
                                else {
                                    Box(
                                        modifier = Modifier
                                            .size(5.dp)
                                            .background(
                                                color = dynamicColor,
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