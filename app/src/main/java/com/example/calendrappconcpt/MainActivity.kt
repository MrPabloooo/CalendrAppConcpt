package com.example.calendrappconcpt

import android.os.Bundle
import android.widget.CalendarView
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ScrollState
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
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.Room
import coil3.compose.AsyncImage
import com.example.calendrappconcpt.ui.theme.CalendrAppConcptTheme
import java.time.LocalDate
import java.time.format.TextStyle

class MainActivity : ComponentActivity() {

    private lateinit var db: AppDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "AppDatabase"
        )   .fallbackToDestructiveMigration()
            .build()

        enableEdgeToEdge()

        setContent {

            val ViewModel: CalendarViewModel = viewModel(
                factory = CalendarViewModelFactory(
                    db.CalendarItemDao()
                )
            )


            val NowDate = LocalDate.now()


            CalendrAppConcptTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    NavApp(
                        viewModel = ViewModel,
                        )




                    }

            }
        }
    }
}



data class MonthData(
    val name: String,
    val month: Int,
    val year: Int,
    val days: Int
)


@Composable
fun Calendar(
    viewModel: CalendarViewModel,
    onFinish: () -> Unit
) {
    val now = LocalDate.now()

    var selectedDay by remember {
        mutableStateOf(LocalDate.now())

    }

    val months = (0..11).map { offset ->
        val date = now.minusMonths(offset.toLong())

        MonthData(
            name = date.month.getDisplayName(
                TextStyle.FULL_STANDALONE,
                java.util.Locale.getDefault()
            ),
            year = date.year,
            days = date.lengthOfMonth(),
            month = date.monthValue
        )
    }
    var currentMonth by remember {
        mutableIntStateOf(0)
    }

    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {


        Spacer(modifier = Modifier.height(32.dp))


        Row(
            modifier = Modifier.fillMaxWidth()
        ) {

            IconButton(
                onClick = {
                    onFinish()
                }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Previous Month"
                )
            }

        }


        MonthScreen(
            month = months[currentMonth],
            onPrevious = {
                if (currentMonth < months.lastIndex) {
                    currentMonth++
//                    selectedDay = LocalDate.of(months[currentMonth].year, months[currentMonth].month, selectedDay.dayOfMonth)

                }
            },
            onNext = {
                if (currentMonth > 0) {
                    currentMonth--
//                    selectedDay = LocalDate.of(months[currentMonth].year, months[currentMonth].month, selectedDay.dayOfMonth)
                }

            },
            SelectedDayUnit = {
                day, month, year ->
                selectedDay = LocalDate.of(year, month, day)

            },
            selectedDay = selectedDay,
            viewModel = viewModel
        )

//        Text(text = "Selected Day: ${selectedDay.dayOfMonth}/${selectedDay.monthValue}/${selectedDay.year}")

        NotesForSpecDay(
            Date = selectedDay,
            viewModel = viewModel
        )
    }




}


@Composable
fun MonthScreen(
    month: MonthData,
    onPrevious: () -> Unit,
    onNext: () -> Unit,
    SelectedDayUnit: (day: Int, month: Int, year: Int) -> Unit,
    selectedDay: LocalDate,
    viewModel: CalendarViewModel
) {






    val dynamicColor = if (isSystemInDarkTheme()) Color.White else Color.Black





    Column(
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            IconButton(
                onClick = {
                    onPrevious()
                },

                ) {

                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Previous Month"
                )

            }


            if (month.year != LocalDate.now().year) {
                Text(
                    text = "${month.name} ${month.year} ".uppercase(),
                    fontWeight = Bold,
                    fontSize = 32.sp
                )
            }
            else {
                Text(
                    text = ("${month.name}").uppercase(),
                    fontWeight = Bold,
                    fontSize = 32.sp

                )
            }


            IconButton(
                onClick = {
                    onNext()
                },

                ) {

                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = "Previous Month"
                )

            }

        }

        Spacer(modifier = Modifier.height(32.dp))




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
                                    .clickable(
                                    indication = null,
                                    interactionSource = remember { MutableInteractionSource() }
                                ) {
                                        SelectedDayUnit(day, month.month, month.year)
                                    }
                                    .padding(4.dp)
                                    .weight(1f)
                                    .aspectRatio(1f)
                                    .border(

                                        width =

                                            if (selectedDay.dayOfMonth == day && selectedDay.monthValue == month.month && selectedDay.year == month.year) {
                                                1.dp
                                            }
                                        else {
                                                3.dp
                                            },

                                        color = dynamicColor,
                                        shape = CircleShape
                                    )
                                    .padding(10.dp),
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
                                    .clickable(
                                        indication = null,
                                        interactionSource = remember { MutableInteractionSource() }
                                    ) {
                                        SelectedDayUnit(day, month.month, month.year)
                                    }
                                    .padding(4.dp)

//                            .size(60.dp)
                                    .weight(1f)
                                    .aspectRatio(1f)
                                    .border(
                                        width = 1.dp,
                                        color =
                                            if (selectedDay.dayOfMonth == day && selectedDay.monthValue == month.month && selectedDay.year == month.year) {
                                                dynamicColor
                                            }
                                        else {
                                            Color.Transparent
                                            }


                                        ,
                                        shape = CircleShape
                                    )
                                    .padding(5.dp),

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



@Composable
fun MonthScreen1(
    month: MonthData,
    onPrevious: () -> Unit,
    onNext: () -> Unit,
    SelectedDayUnit: (day: Int, month: Int, year: Int) -> Unit,
    selectedDay: LocalDate
) {






    Column(
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            IconButton(
                onClick = {
                    onPrevious()
                },

                ) {

                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Previous Month"
                )

            }


            if (month.year != LocalDate.now().year) {
                Text(
                    text = "${month.name} ${month.year} ".uppercase(),
                    fontWeight = Bold,
                    fontSize = 32.sp
                )
            }
            else {
                Text(
                    text = ("${month.name}").uppercase(),
                    fontWeight = Bold,
                    fontSize = 32.sp

                )
            }


            IconButton(
                onClick = {
                    onNext()
                },

                ) {

                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = "Previous Month"
                )

            }

        }

        Spacer(modifier = Modifier.height(32.dp))



        Column(
            modifier = Modifier.wrapContentWidth()
                .verticalScroll(rememberScrollState())
        ) {


            for (weekStart in 1..month.days step 7) {


                Row(
                ) {
                    val endDay = minOf(weekStart + 7, month.days + 1)

                    for (day in weekStart until minOf(weekStart + 7, month.days + 1)) {
                        Box(
                            modifier = Modifier
                                .clickable(
                                    indication = null,
                                    interactionSource = remember { MutableInteractionSource() }
                                ) {
                                    SelectedDayUnit(day, month.month, month.year)
                                }
                                .padding(4.dp)
//                            .size(60.dp)
                                .weight(1f)
                                .aspectRatio(1f)
                                .background(color =
                                    if (LocalDate.now().dayOfMonth == day && LocalDate.now().monthValue == month.month && LocalDate.now().year == month.year) {
                                        if(selectedDay.dayOfMonth == day && selectedDay.monthValue == month.month && selectedDay.year == month.year) {
                                            Color.Red
                                        }
                                        else {
                                            Color.Cyan

                                        }
                                    }

                                    else {
                                        if (selectedDay.dayOfMonth == day && selectedDay.monthValue == month.month && selectedDay.year == month.year) {
                                            Color.Green
                                        }
                                        else {
                                            Color.Blue
                                        }
                                    }
                                ),

                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = day.toString())
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

@Composable
fun NotesForSpecDay(
    Date: LocalDate,
    viewModel: CalendarViewModel
) {


    key(Date) {


        val notes by viewModel
            .getNotes(Date)
            .collectAsState(initial = emptyList())

        if (notes.isEmpty()) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "No notes for this day",
                    fontWeight = Bold,
                    fontSize = 32.sp)

            }
        }
        else {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .verticalScroll(
                        remember(Date) {
                            ScrollState(0)
                        }
                    ),
            ) {
//        Text(text = "Day: $Date")
//
//
//
//        Text("Notes count: ${notes.size}")

                notes.forEach { item ->
//            Text(text = item.title,)
//            Text(text = item.Contents)
//            Text(text = item.date.toString())
//            Spacer(modifier = Modifier.height(8.dp))

                    CardScreen(
                        Title = item.title,
                        Icon = item.Icon,
                        Contents = item.Contents,
                        AdditionalInfo = "",
                        Date = item.date.toString(),
                        Refreshable = false,
                        color = item.color.toColorPaterns()

                    )

                }
            }
        }
    }
}
