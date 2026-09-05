package com.example.calendrappconcpt

import android.graphics.Color.alpha
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import coil3.compose.AsyncImage
import java.time.LocalDate
import kotlin.math.roundToInt

@Composable
fun AdderScreen(
    viewModel: CalendarViewModel,
    onFinish: () -> Unit
) {

    val context = LocalContext.current

    var title by remember { mutableStateOf("") }
    var contents by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = "Adder")


        InputTextElement(
            title = "Title",
            hint = "Title",
            value = title,
            onValueChange = {
                title = it
            }
        )

        InputTextElement(
            title = "Contents",
            hint = "Contents",
            value = contents,
            onValueChange = {
                contents = it
            }
        )


        var iconSelected by remember {
            mutableStateOf(NoteIcon.DEFAULT)
        }


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            verticalAlignment = Alignment.CenterVertically
        ) {
            for (icon in NoteIcon.values()) {
                IconButton(
                    onClick = {
                        iconSelected = icon
                    }
                ) {

                    AsyncImage(
                        model = "file:///android_asset/${icon.toSvgName()}",
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(
                            if (iconSelected == icon) Color.Red else Color.Black
                        )                    )

                }

            }
        }




        var selectedColor by remember {
            mutableStateOf(ColorsOfNotes.Yellow)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            verticalAlignment = Alignment.CenterVertically

        ) {
            for (color in ColorsOfNotes.values()) {

                Box(

                        modifier = Modifier
                            .padding(5.dp)
                            .size(50.dp)
                            .background(
                                Color(color.toColorPaterns().primery).copy(
                                    alpha = if (selectedColor != color) {
                                        0.25f
                                    } else {
                                        1f
                                    }
                                ),

                                shape = RoundedCornerShape(5.dp)
                            )
                            .border(
                                width = 1.dp,
                                color = Color(color.toColorPaterns().primery),
                                shape = RoundedCornerShape(5.dp)
                            )


                            .clickable {
                                selectedColor = color
                            }




                ) {



                }
            }

        }

        CardScreen(
            Title = title,
            Icon = iconSelected,
            Contents = contents,
            AdditionalInfo = "",
            Date = "",
            Refreshable = false,
            color = selectedColor.toColorPaterns()
        )



        Button(
            onClick = {
                if (title.isEmpty() || contents.isEmpty()) {
                    Toast.makeText(
                        context,
                        "Please fill all fields",
                        Toast.LENGTH_SHORT
                    ).show()
                    return@Button
                } else {
                    viewModel.addCalendarItem(
                        CalendarItem(
                            title = title,
                            Icon = iconSelected,
                            Contents = contents,
                            date = LocalDate.now(),
                            color = selectedColor
                        )
                    )
                    onFinish()
                }
            }
        )
        {
            Text(text = "Add note")
        }




    }

}


@Composable
fun InputTextElement(
    title: String,
    hint: String,
    value: String,
    onValueChange: (String) -> Unit

) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "Title")



        OutlinedTextField(
            value = value,

            onValueChange = onValueChange,

            modifier = Modifier.width(150.dp),
            singleLine = true,
//            keyboardOptions = KeyboardOptions(
//                keyboardType = KeyboardType.Number
//            ),
            placeholder = {
                Text(text = "Title")
            }
        )


    }
}