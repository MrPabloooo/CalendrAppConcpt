package com.example.calendrappconcpt

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

fun NoteIcon.toSvgName(): String {
    return when (this) {
        NoteIcon.DEFAULT -> "1.svg"
        NoteIcon.WORK -> "2.svg"
        NoteIcon.SCHOOL -> "3.svg"
        NoteIcon.IDEA -> "4.svg"
        NoteIcon.MUSIC -> "5.svg"
        NoteIcon.MOVIE -> "6.svg"
        NoteIcon.SHOPPING -> "7.svg"
        NoteIcon.IMPORTANT -> "7.svg"
        NoteIcon.SCENE -> "7.svg"
        NoteIcon.DEF -> "7.svg"
        NoteIcon.RANDOMVALUE -> "7.svg"
        NoteIcon.KOTLIN -> "7.svg"
        NoteIcon.ROSY -> "7.svg"
        NoteIcon.PORANEK -> "7.svg"
        NoteIcon.IKONA -> "7.svg"
    }
}