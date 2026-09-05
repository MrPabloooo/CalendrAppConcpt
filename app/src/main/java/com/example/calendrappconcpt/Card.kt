
package com.example.calendrappconcpt

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.material3.Icon
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.style.LineHeightStyle
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
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.BlendMode.Companion.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.error
import coil3.request.fallback
import coil3.request.placeholder

@Composable
fun CardScreenLegacy(
    Title: String,
    Icon: NoteIcon,
    Contents: String,
    AdditionalInfo: String,
    Date: String,
    Refreshable: Boolean,
    OnRefresh: () -> Unit = {},
    color: ColorPaterns,
) {
    val lineHeight = 25.dp

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
                            color = Color(color.primery),
                            cornerRadius = CornerRadius(radius, radius)
                        )

                        // Linie
                        val lineSpacing = 25.dp.toPx()
                        var y = 70.dp.toPx()

                        while (y < size.height) {
                            drawLine(
                                color = Color(color.liness),
                                start = Offset(0f, y),
                                end = Offset(size.width, y),
                                strokeWidth = 2f
                            )

                            y += lineSpacing
                        }

                        // Czerwony margines
                        drawLine(
                            color = Color(color.margines),
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
//
//            Text(
//                text = "",
////            modifier = Modifier.fillMaxWidth(),
////            textAlign = TextAlign.Center,
//                fontWeight = Bold,
//                color =  Color(color.textColor),
//                fontSize = 24.sp,
//                lineHeight = lineHeight
//            )


            AsyncImage(
                model = "file:///android_asset/${Icon.toSvgName()}",

                contentDescription = "Icon",
                colorFilter = ColorFilter.tint(Color(color.textColor)),
                modifier = Modifier.size(48.dp)
            )



            Text(
                text = Title,
                fontWeight = Bold,
                color = Color(color.textColor),
                fontSize = 24.sp,
                lineHeight = lineHeight.value.sp,
                style = TextStyle(
                    platformStyle = PlatformTextStyle(
                        includeFontPadding = false
                    ),
//                    lineHeightStyle = LineHeightStyle(
//                        alignment = LineHeightStyle.Alignment.Center,
//                        trim = LineHeightStyle.Trim.None
//                    )
                    lineHeightStyle = LineHeightStyle(
                        alignment = LineHeightStyle.Alignment.Proportional,
                        trim = LineHeightStyle.Trim.None
                    )
                )
            )








            Text(
                text = Contents,
                fontWeight = Bold,
                color = Color(color.textColor),
                fontSize = 12.sp,
                lineHeight = lineHeight.value.sp,
                style = TextStyle(
                    platformStyle = PlatformTextStyle(
                        includeFontPadding = false
                    ),
//                    lineHeightStyle = LineHeightStyle(
//                        alignment = LineHeightStyle.Alignment.Center,
//                        trim = LineHeightStyle.Trim.None
//                    )
                    lineHeightStyle = LineHeightStyle(
                        alignment = LineHeightStyle.Alignment.Proportional,
                        trim = LineHeightStyle.Trim.None
                    )
                )
            )

            Text(
                text = "",
                fontSize = 12.sp,
                color = Color(color.textColor),
                lineHeight = lineHeight.value.sp,
            )


            Text(
                text = AdditionalInfo,
                fontWeight = Bold,
                color = Color(color.textColor),
                fontSize = 12.sp,
                lineHeight = lineHeight.value.sp,
                style = TextStyle(
                    platformStyle = PlatformTextStyle(
                        includeFontPadding = false
                    ),
//                    lineHeightStyle = LineHeightStyle(
//                        alignment = LineHeightStyle.Alignment.Center,
//                        trim = LineHeightStyle.Trim.None
//                    )
                    lineHeightStyle = LineHeightStyle(
                        alignment = LineHeightStyle.Alignment.Proportional,
                        trim = LineHeightStyle.Trim.None
                    )
                )
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




//@Preview(showBackground = true)
//@Composable
//fun CardScreenPreviewLegacy() {
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp),
//        contentAlignment = Alignment.Center
//    ) {
//        CardScreen(
//            Title = "Notatkaaaa ",
//            Icon = NoteIcon.HOTDOG,
//            Contents = "To jest treść mojej notatki która również może być dłuższa i zawijać się na kolejne linie. Fajne cn???",
//            AdditionalInfo = "67 days ago",
//            Date = "03.09.2026",
//            Refreshable = false,
//            color = ColorsOfNotes.Blueprint.toColorPaterns()
//        )
//    }
//
//}






@Composable
private fun LinedText(
    text: String,
    fontSize: TextUnit,
    lineHeight: TextUnit,
    color: Color,
    lineColor: Color,
    horizontalOffset: Dp = 0.dp,
    fontWeight: FontWeight = Bold
) {
    var layoutResult by remember {
        mutableStateOf<TextLayoutResult?>(null)
    }

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
    Text(
        text = text,

        modifier = Modifier
            .fillMaxWidth()
            .drawBehind {

                layoutResult?.let { layout ->

                    for (line in 0 until layout.lineCount) {

                        val baseline = layout.getLineBaseline(line)

                        val lineY = baseline + 5.dp.toPx()

                        drawLine(
                            color = lineColor,

                            start = Offset(
                                horizontalOffset.toPx(),
                                lineY
                            ),

                            end = Offset(
                                size.width + horizontalOffset.toPx(),
                                lineY
                            ),

                            strokeWidth = 2f
                        )
                    }
                }
            },

        fontWeight = fontWeight,
        fontSize = fontSize,
        lineHeight = lineHeight,
        color = color,

        onTextLayout = {
            layoutResult = it
        },

        style = TextStyle(
            platformStyle = PlatformTextStyle(
                includeFontPadding = false
            ),

            lineHeightStyle = LineHeightStyle(
                alignment = LineHeightStyle.Alignment.Center,
                trim = LineHeightStyle.Trim.None
            )
        )
    )

    }
}

@Composable
fun CardScreen(
    Title: String,
    Icon: NoteIcon,
    Contents: String,
    AdditionalInfo: String,
    Date: String,
    Refreshable: Boolean,
    OnRefresh: () -> Unit = {},
    color: ColorPaterns,
) {

    val paperColor = Color(color.primery)
    val textColor = Color(color.textColor)
    val lineColor = Color(color.liness)
    val marginColor = Color(color.margines)

    val clipboard = LocalClipboardManager.current

    Column (

    ){


        Column(
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth()
//                    .clickable(
//                    onClick = {
//                        clipboard.setText(AnnotatedString(Contents))
//
//                    }
//                    )
                .drawBehind {

                    val radius = 40.dp.toPx()

                    clipPath(
                        path = Path().apply {
                            addRoundRect(
                                androidx.compose.ui.geometry.RoundRect(
                                    left = 0f,
                                    top = 0f,
                                    right = size.width,
                                    bottom = size.height,
                                    cornerRadius = CornerRadius(
                                        radius,
                                        radius
                                    )
                                )
                            )
                        }
                    ) {

                        /*
                         * KARTKA
                         */
                        drawRoundRect(
                            color = paperColor,
                            cornerRadius = CornerRadius(
                                radius,
                                radius
                            )
                        )


                        /*
                         * CZERWONY MARGINES
                         */
                        drawLine(
                            color = marginColor,

                            start = Offset(
                                30.dp.toPx(),
                                0f
                            ),

                            end = Offset(
                                30.dp.toPx(),
                                size.height
                            ),

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

            /*
             * IKONA
             */

            AsyncImage(

                model = "file:///android_asset/${Icon.toSvgName()}",


                contentDescription = "Icon",

                colorFilter = ColorFilter.tint(
                    textColor
                ),

                modifier = Modifier.size(48.dp)
            )


            /*
             * TYTUŁ
             *
             * Jeżeli Title jest długi i zawija się na:
             *
             * linia 1
             * linia 2
             * linia 3
             *
             * każda z tych linii dostanie własną
             * linię zeszytu.
             */

            LinedText(
                text = Title,

                fontSize = 24.sp,
                lineHeight = 30.sp,

                color = textColor,
                lineColor = lineColor,
                fontWeight = Bold
            )


            /*
             * TREŚĆ
             */

            LinedText(
                text = Contents,

                fontSize = 12.sp,
                lineHeight = 30.sp,

                color = textColor,
                lineColor = lineColor,
                fontWeight = Bold
            )


            /*
            * SPACER
             */

            LinedText(
                text = "",

                fontSize = 12.sp,
                lineHeight = 30.sp,

                color = textColor,
                lineColor = lineColor,
                fontWeight = Bold
            )


            /*
             * DODATKOWE INFO
             */

            LinedText(
                text = AdditionalInfo,

                fontSize = 12.sp,
                lineHeight = 30.sp,

                color = textColor,
                lineColor = lineColor,
                fontWeight = Bold
            )
        }


        /*
         * REFRESH
         */

        if (Refreshable) {

            Row(
                modifier = Modifier.fillMaxWidth(),

                horizontalArrangement =
                    Arrangement.SpaceBetween
            ) {

                Box { }

                IconButton(
                    onClick = OnRefresh
                ) {

                    Icon(
                        imageVector = Icons.Default.Refresh,
                        contentDescription = "Refresh"
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun CardScreenPreview() {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),

        contentAlignment = Alignment.Center
    ) {

        CardScreen(

            Title =
                "Notatkaaaaaaaaa😂😂😂😂😂😂😂😂😂😂😂😂😂😂",

            Icon = NoteIcon.HOTDOG,

            Contents =
                "To jest treść mojej notatki która również " +
                        "może być dłuższa i zawijać się na kolejne " +
                        "linie. Fajne cn??? To jest kolejna część " +
                        "tekstu żeby sprawdzić wiele wierszy. Notatkaaaaaaaaa😂😂😂😂😂😂😂😂😂😂😂😂😂😂",

            AdditionalInfo = "67 days ago",

            Date = "03.09.2026",

            Refreshable = false,

            color =
                ColorsOfNotes.Blueprint
                    .toColorPaterns()
        )
    }
}



@Composable
private fun LinedTextField(
    value: String,
    onValueChange: (String) -> Unit,

    hint: String,

    fontSize: TextUnit,
    lineHeight: TextUnit,

    color: Color,
    lineColor: Color,

    horizontalOffset: Dp = 0.dp,

    fontWeight: FontWeight = Bold,

    modifier: Modifier = Modifier
) {

    var layoutResult by remember {
        mutableStateOf<TextLayoutResult?>(null)
    }

    BasicTextField(
        value = value,

        onValueChange = onValueChange,

        modifier = modifier
            .fillMaxWidth()
            .drawBehind {

                layoutResult?.let { layout ->

                    for (line in 0 until layout.lineCount) {

                        val baseline =
                            layout.getLineBaseline(line)

                        val lineY =
                            baseline + 5.dp.toPx()

                        drawLine(

                            color = lineColor,

                            start = Offset(
                                horizontalOffset.toPx(),
                                lineY
                            ),

                            end = Offset(
                                size.width + horizontalOffset.toPx(),
                                lineY
                            ),

                            strokeWidth = 2f
                        )
                    }
                }
            },

        textStyle = TextStyle(

            fontWeight = fontWeight,

            fontSize = fontSize,

            lineHeight = lineHeight,

            color = color,

            platformStyle = PlatformTextStyle(
                includeFontPadding = false
            ),

            lineHeightStyle = LineHeightStyle(
                alignment =
                    LineHeightStyle.Alignment.Center,

                trim =
                    LineHeightStyle.Trim.None
            )
        ),

        onTextLayout = {
            layoutResult = it
        },

        cursorBrush = SolidColor(color),


        decorationBox = { innerTextField ->
            Box {
                if (value.isEmpty()) {
                    Text(
                        text = "Enter ${hint}...",
                        fontWeight = fontWeight,
                        fontSize = fontSize,
                        lineHeight = lineHeight,
                        color = color.copy(alpha = 0.4f)
                    )
                }

                innerTextField()
            }
        }
    )
}


@Composable
fun CardScreen1(

    Title: String,

    Icon: NoteIcon,

    Contents: String,

    onTitleChange: (String) -> Unit,

    onContentsChange: (String) -> Unit,

    color: ColorPaterns,

    ) {

    val paperColor =
        Color(color.primery)

    val textColor =
        Color(color.textColor)

    val lineColor =
        Color(color.liness)

    val marginColor =
        Color(color.margines)


    Column {

        Column(

            modifier = Modifier

                .padding(5.dp)

                .fillMaxWidth()

                .drawBehind {

                    val radius =
                        40.dp.toPx()


                    clipPath(

                        path = Path().apply {

                            addRoundRect(

                                androidx.compose.ui.geometry.RoundRect(

                                    left = 0f,

                                    top = 0f,

                                    right = size.width,

                                    bottom = size.height,

                                    cornerRadius =
                                        CornerRadius(
                                            radius,
                                            radius
                                        )
                                )
                            )
                        }

                    ) {

                        /*
                         * KARTKA
                         */

                        drawRoundRect(

                            color = paperColor,

                            cornerRadius =
                                CornerRadius(
                                    radius,
                                    radius
                                )
                        )


                        /*
                         * CZERWONY MARGINES
                         */

                        drawLine(

                            color = marginColor,

                            start = Offset(

                                30.dp.toPx(),

                                0f
                            ),

                            end = Offset(

                                30.dp.toPx(),

                                size.height
                            ),

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


            /*
             * IKONA
             */

            AsyncImage(

                model =
                    "file:///android_asset/${Icon.toSvgName()}",



                contentDescription = "Icon",

                colorFilter =
                    ColorFilter.tint(textColor),

                modifier =
                    Modifier.size(48.dp)
            )


            /*
             * TYTUŁ
             */

            LinedTextField(

                value = Title,

                onValueChange =
                    onTitleChange,

                fontSize = 24.sp,

                lineHeight = 30.sp,

                color = textColor,

                lineColor = lineColor,

                fontWeight = Bold,
                hint = "title"
            )


            /*
             * TREŚĆ
             */

            LinedTextField(

                value = Contents,

                onValueChange =
                    onContentsChange,

                fontSize = 12.sp,

                lineHeight = 30.sp,

                color = textColor,

                lineColor = lineColor,

                fontWeight = Bold,
                hint = "text"
            )



        }


    }
}



