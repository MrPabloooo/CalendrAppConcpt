package com.example.calendrappconcpt

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "CalendarItem")
data class CalendarItem(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val title: String,
    val Icon: String,
    val Contents: String,
    val date: LocalDate


)
