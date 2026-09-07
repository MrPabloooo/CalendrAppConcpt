package com.example.calendrappconcpt

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
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

    fun getAllNotes(): Flow<List<CalendarItem>> {
        return dao.getAll()
    }
    val calendarItemsCount: Flow<Int> =
        dao.getAll().map { it.size }



    fun getRandomNoteForSpecDay(date: LocalDate): Flow<CalendarItem?> {
        return dao.getNoteForDay(
            date.format(DateTimeFormatter.ISO_LOCAL_DATE)
        ).map { notes ->
            notes.randomOrNull()
        }
    }

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


    data class Streak(
        val current: Int,
        val longest: Int,
        val lastActiveDate: LocalDate?
    )

    val streak: StateFlow<Streak?> =
        dao.getActivityDates()
            .map { dates ->
                calculateStreak(dates)
            }
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5000),
                null
            )



    fun calculateStreak(
        dates: List<LocalDate>
    ): Streak {

        if (dates.isEmpty()) {
            return Streak(
                current = 0,
                longest = 0,
                lastActiveDate = null
            )
        }

        val today = LocalDate.now()

        var current = 0

        if (
            dates.first() == today ||
            dates.first() == today.minusDays(1)
        ) {
            current = 1

            for (i in 1 until dates.size) {

                if (dates[i] == dates[i - 1].minusDays(1)) {
                    current++
                } else {
                    break
                }
            }
        }

        var longest = 1
        var temp = 1

        for (i in 1 until dates.size) {

            if (dates[i] == dates[i - 1].minusDays(1)) {
                temp++
                longest = maxOf(longest, temp)
            } else {
                temp = 1
            }
        }

        return Streak(
            current = current,
            longest = longest,
            lastActiveDate = dates.first()
        )
    }




}