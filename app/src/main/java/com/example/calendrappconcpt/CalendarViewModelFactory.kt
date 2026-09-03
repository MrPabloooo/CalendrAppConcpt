package com.example.calendrappconcpt

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CalendarViewModelFactory(
    private val dao: CalendarItemDao
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(CalendarViewModel::class.java)) {
            return CalendarViewModel(
                dao
            ) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}