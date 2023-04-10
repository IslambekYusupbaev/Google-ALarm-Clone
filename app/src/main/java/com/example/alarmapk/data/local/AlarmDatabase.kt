package com.example.alarmapk.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.alarmapk.data.models.AlarmData

@Database(entities = [AlarmData::class], version = 3)
abstract class AlarmDatabase() : RoomDatabase() {
    abstract fun getAlarmDao(): AlarmDao

    companion object {
        const val DATABASE_NAME = "db_name"

        fun getInstance(context: Context): AlarmDatabase {
            return Room.databaseBuilder(
                context, AlarmDatabase::class.java, DATABASE_NAME
            ).fallbackToDestructiveMigration().build()
        }
    }


}