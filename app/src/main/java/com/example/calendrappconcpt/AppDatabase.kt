package com.example.calendrappconcpt

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
entities = [CalendarItem::class],
version = 1
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun CalendarItemDao(): CalendarItemDao
}
