package com.example.calendrappconcpt

import androidx.compose.ui.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import java.time.LocalDate

@Entity(tableName = "CalendarItem")
data class CalendarItem(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val title: String,
    val Icon: NoteIcon,
    val Contents: String,
    val date: LocalDate,
    val color: ColorsOfNotes


)

@Entity
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val title: String,
    val content: String,

    val icon: NoteIcon = NoteIcon.DEFAULT,


)

class Converter {

    @TypeConverter
    fun fromNoteIcon(icon: NoteIcon): String {
        return icon.name
    }

    @TypeConverter
    fun toNoteIcon(value: String): NoteIcon {
        return NoteIcon.valueOf(value)
    }

    @TypeConverter
    fun fromColorsOfNotes(color: ColorsOfNotes): String {
        return color.name
    }

    @TypeConverter
    fun toColorsOfNotes(value: String): ColorsOfNotes {
        return ColorsOfNotes.valueOf(value)
    }

    @TypeConverter
    fun fromColor(color: Color): Long {
        return color.value.toLong()
    }
    @TypeConverter
    fun toColor(value: Long): Color {
        return Color(value.toULong())
    }

}