package com.example.alarmapk.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
//import com.example.alarmapk.data.models.AddDate
import com.example.alarmapk.data.models.AlarmData


@Dao
interface AlarmDao {
    @Query("SELECT * FROM AlarmVip")
    suspend fun getAllAlarm(): List<AlarmData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAlarm(alarm: AlarmData)
//
//    @Insert(entity = AddDate::class)
//    suspend fun addDayMonth(addDate: AddDate)

}