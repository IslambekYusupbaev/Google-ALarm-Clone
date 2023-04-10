package com.example.alarmapk.ui.clock

import android.app.DatePickerDialog
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.alarmapk.R
import com.example.alarmapk.data.local.AlarmDao
import com.example.alarmapk.data.local.AlarmDatabase
//import com.example.alarmapk.data.models.AddDate
import com.example.alarmapk.data.models.AlarmData
import com.example.alarmapk.databinding.FragmentClockBinding
import com.example.alarmapk.ui.alarm.adapter.AlarmAdapter
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.MaterialTimePicker.INPUT_MODE_CLOCK
import com.google.android.material.timepicker.TimeFormat
import kotlinx.coroutines.launch
import java.util.*
import javax.xml.datatype.DatatypeConstants.MONTHS

class ClockFragment : Fragment(R.layout.fragment_clock) {
    private lateinit var binding: FragmentClockBinding
    private lateinit var dao: AlarmDao

    var adapter = AlarmAdapter()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentClockBinding.bind(view)
        dao = AlarmDatabase.getInstance(requireContext()).getAlarmDao()

        binding.rvClock.adapter = adapter


        lifecycleScope.launch {
            adapter.models = dao.getAllAlarm().toMutableList()
        }
//        adapter.ivDateClick { alarmdata,dayMont->
//            val c = Calendar.getInstance()
//            val year = c.get(Calendar.YEAR)
//            val month = c.get(Calendar.MONTH)
//            val day = c.get(Calendar.DAY_OF_MONTH)
//            val dpd = DatePickerDialog(requireActivity(), DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
//                // Display Selected date in textbox
//                dayMont.setText("""$dayOfMonth-$monthOfYear """)
//            }, year, month, day)
//            dpd.show()
//
//            val dayMonth =AddDate(
//                dayMonth = "$day - $month",
//                added = false
//
//            )
//            dao.addDayMonth(dayMonth)
//
//
//
//
////        val c = Calendar.getInstance()
////        val myFormat = "dd-MM-yyyy"
////        val sdf = SimpleDateFormat(myFormat, Locale.UK)
////            val date = sdf.format(c.time)
//
//
//        }

        binding.ivAdd.setOnClickListener {
            val timePickerDialog =
                MaterialTimePicker.Builder().setTimeFormat(TimeFormat.CLOCK_24H).setHour(12)
                    .setTheme(R.style.MyCustomTimePicker).setMinute(0)
                    .setInputMode(INPUT_MODE_CLOCK).setTitleText("Vremya").build()

            timePickerDialog.show(childFragmentManager, "tag")
            timePickerDialog.addOnPositiveButtonClickListener {
                lifecycleScope.launch {
                    if (timePickerDialog.minute < 10) {
                        dao.addAlarm(
                            AlarmData(
                                0,
                                "${timePickerDialog.hour}:0${timePickerDialog.minute}"
                            )
                        )
                    } else {
                        dao.addAlarm(
                            AlarmData(
                                0,
                                "${timePickerDialog.hour}:${timePickerDialog.minute}"
                            )
                        )
                    }
                    adapter.models = dao.getAllAlarm().sortedBy { it.time }.reversed().toMutableList()
                }
            }


        }



    }

}