package com.example.calendrappconcpt

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

@Dao
interface CalendarItemDao {


    @Insert suspend fun insert(Item: CalendarItem)

    @Query("SELECT * FROM calendaritem ORDER BY date desc")
    fun getAll() : Flow<List<CalendarItem>>

    @Query("SELECT * FROM calendaritem WHERE id = :id")
    suspend fun getNote(id: Long): CalendarItem?

    @Query("""
    SELECT * FROM calendaritem 
    WHERE date = :date
    ORDER BY date DESC
""")
    fun getNoteForDay(date: String): Flow<List<CalendarItem>>

    @Query("SELECT * FROM calendaritem ORDER BY RANDOM() LIMIT 1")
    suspend fun getRandomItem(): CalendarItem?


    @Query("SELECT * FROM calendaritem WHERE date = :date ORDER BY RANDOM() LIMIT 1")
    suspend fun getRandomItemByDate(date: LocalDate): CalendarItem?

    @Query("SELECT DISTINCT date FROM CalendarItem ORDER BY date DESC")
    fun getActivityDates(): Flow<List<LocalDate>>


}