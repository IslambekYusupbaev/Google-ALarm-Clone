package com.example.alarmapk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.alarmapk.data.local.AlarmDao
import com.example.alarmapk.data.local.AlarmDatabase
import com.example.alarmapk.databinding.ActivityMainBinding
import com.example.alarmapk.ui.alarm.adapter.AlarmAdapter

class MainActivity : AppCompatActivity() {
    lateinit var  dao : AlarmDao
    lateinit var binding: ActivityMainBinding
    private val adapter= AlarmAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dao = AlarmDatabase.getInstance(this).getAlarmDao()

        val navHostFragment =supportFragmentManager.findFragmentById(R.id.container_view ) as NavHostFragment
        binding.bottomNovigation.setupWithNavController(navHostFragment.navController)

       // AddTime()


    }
//    private fun AddTime(){
//        binding.ivAdd.setOnClickListener {
//            val materialTimePicker = MaterialTimePicker.Builder().setTimeFormat(TimeFormat.CLOCK_24H).setHour(12).setTheme(
//                androidx.transition.R.style.AlertDialog_AppCompat).setMinute(0).setInputMode(INPUT_MODE_CLOCK).setTitleText("vremya").build()
//            materialTimePicker.show(supportFragmentManager,"tag")
//
////            materialTimePicker.addOnPositiveButtonClickListener {
////                lifecycleScope.launchWhenResumed {
////                    if(materialTimePicker.minute <10){
////                        dao.addAlarm(
////                            Alarm(0,"${materialTimePicker.hour}: 0${materialTimePicker.minute}")
////                        )
////                    }
////                    else {
////                        dao.addAlarm(
////                            Alarm(0,"${materialTimePicker.hour}: ${materialTimePicker.minute}")
////                        )
////
////                    }
////                    adapter.models =dao.getAllAlarm().toMutableList().sortedBy {
////                        it.time
////                    }.reversed().toMutableList()
////
////
////                }
////            }
//
//        }
//    }
}