package com.example.calendrappconcpt

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

fun NoteIcon.toImageVector(): ImageVector {
    return when (this) {
        NoteIcon.DEFAULT -> Icons.Default.ShoppingCart
        NoteIcon.WORK -> Icons.Default.Favorite
        NoteIcon.SCHOOL -> Icons.Default.Notifications
        NoteIcon.IDEA -> Icons.Default.Lock
        NoteIcon.MUSIC -> Icons.Default.Person
        NoteIcon.MOVIE -> Icons.Default.Star
        NoteIcon.SHOPPING -> Icons.Default.ShoppingCart
        NoteIcon.IMPORTANT -> Icons.Default.Done
        NoteIcon.SCENE -> Icons.Default.Favorite
        NoteIcon.DEF -> Icons.Default.ShoppingCart
        NoteIcon.RANDOMVALUE -> Icons.Default.ShoppingCart
        NoteIcon.KOTLIN -> Icons.Default.ShoppingCart
        NoteIcon.ROSY -> Icons.Default.ShoppingCart
        NoteIcon.PORANEK -> Icons.Default.ShoppingCart
        NoteIcon.IKONA -> Icons.Default.ShoppingCart

    }
}