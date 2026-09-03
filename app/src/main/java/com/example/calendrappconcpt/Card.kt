
package com.example.calendrappconcpt

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.material3.Text
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.BlendMode.Companion.Color
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
@Composable
fun CardScreen(
    Title: String,
    Icon: String,
    Contents: String,
    AdditionalInfo: String,
    Date: String,
    Refreshable: Boolean,
    OnRefresh: () -> Unit = {}
) {
    val lineHeight = 25.sp

    Column(
        modifier = Modifier
    ) {
        Column(
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth()
                .drawBehind {
                    val radius = 40.dp.toPx()

                    // Przytnij wszystko do rounded rect
                    clipPath(
                        path = androidx.compose.ui.graphics.Path().apply {
                            addRoundRect(
                                androidx.compose.ui.geometry.RoundRect(
                                    left = 0f,
                                    top = 0f,
                                    right = size.width,
                                    bottom = size.height,
                                    cornerRadius = CornerRadius(radius, radius)
                                )
                            )
                        }
                    ) {
                        // Tło kartki
                        drawRoundRect(
                            color = Color(0xFFFFF59D),
                            cornerRadius = CornerRadius(radius, radius)
                        )

                        // Linie
                        val lineSpacing = lineHeight.toPx()
                        var y = 70.dp.toPx()

                        while (y < size.height) {
                            drawLine(
                                color = Color(0xFFB0BEC5),
                                start = Offset(0f, y),
                                end = Offset(size.width, y),
                                strokeWidth = 2f
                            )

                            y += lineSpacing
                        }

                        // Czerwony margines
                        drawLine(
                            color = Color(0xFFE57373),
                            start = Offset(30.dp.toPx(), 0f),
                            end = Offset(30.dp.toPx(), size.height),
                            strokeWidth = 3f
                        )
                    }
                }
                .padding(
                    start = 45.dp,
                    end = 25.dp,
                    top = 20.dp,
                    bottom = 20.dp
                )
        ) {

            Text(
                text = "",
//            modifier = Modifier.fillMaxWidth(),
//            textAlign = TextAlign.Center,
                fontWeight = Bold,
                color = Color(0xFF212121),
                fontSize = 24.sp,
                lineHeight = lineHeight
            )

            Text(
                text = Title,
//            modifier = Modifier.fillMaxWidth(),
//            textAlign = TextAlign.Center,
                fontWeight = Bold,
                color = Color(0xFF212121),
                fontSize = 24.sp,
                lineHeight = lineHeight
            )

            Text(
                text = Contents,
                fontSize = 9.sp,
                color = Color(0xFF212121),

                lineHeight = lineHeight
            )

            Text(
                text = "",
                fontSize = 9.sp,
                color = Color(0xFF212121),
                lineHeight = lineHeight
            )

            Text(
                text = AdditionalInfo,
                fontSize = 9.sp,
                color = Color(0xFF212121),
                lineHeight = lineHeight
            )
        }

        if (Refreshable) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween

            ) {
                Box() { }
                IconButton(
                    onClick = {
                        OnRefresh()
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Refresh,
                        contentDescription = "Ref"

                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CardScreenPreview() {
    CardScreen(
        Title = "Wakacje",
        Icon = "Icon",
        Contents = "To jest treść mojej notatki która również może być dłuższa i zawijać się na kolejne linie.",
        AdditionalInfo = "Dodatkowe informacje",
        Date = "03.09.2026",
        Refreshable = true
    )
}

