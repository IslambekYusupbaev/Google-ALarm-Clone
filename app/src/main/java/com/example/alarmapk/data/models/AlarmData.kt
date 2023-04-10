package com.example.alarmapk.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "AlarmVip")
data class AlarmData(
    @PrimaryKey(autoGenerate = true)  val id: Int,
    val time: String,// 15:40
    var isMondayActived: Boolean = false,
    var isTuesdayActived: Boolean = false,
    var isWednesdayActived: Boolean = false,
    var isThursdayActived: Boolean = false,

    var isFridayActived: Boolean = false,
    var isSaturdayActived: Boolean = false,
    var isSundayActived: Boolean = false
)
