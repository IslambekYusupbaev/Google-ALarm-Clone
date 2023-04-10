package com.example.alarmapk.ui.alarm

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.alarmapk.R
import com.example.alarmapk.databinding.FragmentAlarmBinding

class AlarmFragment:Fragment(R.layout.fragment_alarm) {
    private lateinit var binding: FragmentAlarmBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAlarmBinding.bind(view)

    }
}