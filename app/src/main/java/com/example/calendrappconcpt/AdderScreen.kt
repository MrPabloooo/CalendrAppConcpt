package com.example.calendrappconcpt

import android.graphics.Color.alpha
import android.widget.Button
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.error
import coil3.request.fallback
import coil3.request.placeholder
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




//        InputTextElement(
//            title = "Title",
//            hint = "Title",
//            value = title,
//            onValueChange = {
//                title = it
//            }
//        )
//
//        InputTextElement(
//            title = "Contents",
//            hint = "Contents",
//            value = contents,
//            onValueChange = {
//                contents = it
//            }
//        )


        var iconSelected by rememberSaveable {
            mutableStateOf(NoteIcon.DEFAULT)
        }

        Spacer(modifier = Modifier.height(10.dp))


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            IconButton(
                onClick = {
                    onFinish()
                }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Previous"
                )
            }

        }



        Spacer(modifier = Modifier.height(10.dp))

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
                },
                modifier = Modifier.size(48.dp)
            ) {
                AsyncImage(
//                    model = "file:///android_asset/${icon.toSvgName()}",
                    model = ImageRequest.Builder(LocalContext.current)
                        .data("file:///android_asset/${icon.toSvgName()}")
                        // Keep crossfade OFF when using a placeholder of a different shape
                        .placeholder(R.drawable.outline_image_24)
                        .error(R.drawable.outline_image_24)
                        .fallback(R.drawable.outline_image_24)
                        .build(),


                    contentDescription = null,
                    modifier = Modifier.size(28.dp),
                    contentScale = ContentScale.Fit,
                    colorFilter = ColorFilter.tint(
                        if (isSystemInDarkTheme()) {
                            if (iconSelected == icon) Color.White else Color.DarkGray
                        }
                        else {
                            if (iconSelected == icon) Color.Black else Color.Gray

                        }
                    ),

                )
            }

            }
        }





        var selectedColor by rememberSaveable {
            mutableStateOf(ColorsOfNotes.Yellow)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            verticalAlignment = Alignment.CenterVertically

        ) {
            for (color in ColorsOfNotes.values()) {
                val paperColor = Color(if (isSystemInDarkTheme()) color.toColorPaterns().primery else color.toColorPaterns().primeryDark)

                Box(


                modifier = Modifier
                            .padding(5.dp)
                            .size(50.dp)
                            .background(
                                paperColor.copy(
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
                                color = paperColor,
                                shape = RoundedCornerShape(5.dp)
                            )


                            .clickable {
                                selectedColor = color
                            }




                ) {



                }
            }

        }

//        CardScreen(
//            Title = title,
//            Icon = iconSelected,
//            Contents = contents,
//            AdditionalInfo = "",
//            Date = "",
//            Refreshable = false,
//            color = selectedColor.toColorPaterns()
//        )



        CardScreen1(
            Title = title,
            Icon = iconSelected,
            Contents = contents,

            onTitleChange = {
                title = it
            },
            onContentsChange = {
                contents = it
            },
            color = selectedColor.toColorPaterns(),

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
    Column (
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = title)



        OutlinedTextField(
            value = value,

            onValueChange = onValueChange,

            modifier = Modifier.fillMaxWidth(),
//            singleLine = true,
//            keyboardOptions = KeyboardOptions(
//                keyboardType = KeyboardType.Number
//            ),
            placeholder = {
                Text(text = hint)
            }
        )


    }
}