package com.example.calendrappconcpt

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import kotlin.random.Random


class CalendarViewModel(
    private val dao: CalendarItemDao,
) : ViewModel() {

    private val selectedDate = MutableStateFlow(LocalDate.now())


    private fun getDayRange(date: LocalDate): Pair<Long, Long> {

        val start = date
            .atStartOfDay(ZoneId.systemDefault())
            .toInstant()
            .toEpochMilli()

        val end = date
            .plusDays(1)
            .atStartOfDay(ZoneId.systemDefault())
            .toInstant()
            .toEpochMilli()

        return start to end
    }

    fun getNotes(date: LocalDate): Flow<List<CalendarItem>> =
        dao.getNoteForDay(
            date.format(DateTimeFormatter.ISO_LOCAL_DATE)
        )

    fun logDatabase() {
        viewModelScope.launch {

            dao.getAll().collect { products ->

                Log.d(
                    "ROOM_DATABASE",
                    products.toString()
                )

            }

        }
    }


    fun addCalendarItem(Item: CalendarItem) {
        viewModelScope.launch {
            dao.insert(Item)

            logDatabase()

        }
    }


    private val _randomNote = MutableStateFlow<CalendarItem?>(null)
    val randomNote = _randomNote.asStateFlow()

    fun getRandomById() {
        viewModelScope.launch {
            _randomNote.value = dao.getRandomItem()
        }
    }


}