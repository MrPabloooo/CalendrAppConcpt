package com.example.calendrappconcpt

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
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
        modifier = Modifier.fillMaxSize()
            .padding(20.dp),
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
                            Icon = "Icon",
                            Contents = contents,
                            date = LocalDate.now()
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