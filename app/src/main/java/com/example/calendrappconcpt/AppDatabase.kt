package com.example.calendrappconcpt

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
entities = [CalendarItem::class],
version = 4
)
@TypeConverters(Converters::class,
    Converter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun CalendarItemDao(): CalendarItemDao
}

