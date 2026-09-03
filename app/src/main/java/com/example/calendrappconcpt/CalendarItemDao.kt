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
        WHERE date BETWEEN :startOfDay AND :endOfDay
        ORDER BY date DESC
    """)
    fun getProductsForDay(
        startOfDay: Long,
        endOfDay: Long
    ): Flow<List<CalendarItem>>




}