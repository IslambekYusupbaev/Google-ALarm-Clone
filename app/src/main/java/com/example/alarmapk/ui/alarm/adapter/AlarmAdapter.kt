package com.example.alarmapk.ui.alarm.adapter

import android.app.DatePickerDialog
import android.icu.text.SimpleDateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.alarmapk.data.models.AlarmData
import com.example.alarmapk.databinding.ItemAlarmBinding
import java.util.*

class AlarmAdapter : RecyclerView.Adapter<AlarmAdapter.ViewHolder>() {
    private var countClick = 0
    var models = mutableListOf<AlarmData>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    private var onClickAlarmListener: ((AlarmData) -> Unit)? = null
    private var onClickDaysOfWeekListener: ((AlarmData) -> Unit)? = null

    inner class ViewHolder(private val binding: ItemAlarmBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val alarmData = models[adapterPosition]

            binding.tvMonday.setOnClickListener {
                binding.tvMonday.isSelected = alarmData.isMondayActived.not()
                alarmData.isMondayActived = alarmData.isMondayActived.not()
                onClickDaysOfWeekListener?.invoke(alarmData)
            }
            binding.tvSunday.setOnClickListener {
                binding.tvSunday.isSelected = alarmData.isSundayActived.not()
                alarmData.isSundayActived = alarmData.isSundayActived.not()
                onClickDaysOfWeekListener?.invoke(alarmData)
            }

            binding.tvClock.text = alarmData.time
            binding.tvClock.setOnClickListener {
                tvOnClick.invoke(alarmData)
            }


            binding.btnOn.setOnClickListener {
                onClick(binding)
            }
            binding.suspend.setOnClickListener {
                ivData.invoke(alarmData,binding.dayMonth)
//                val myCalendar = Calendar.getInstance()
//                val dataPicker = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
//                    myCalendar.set(Calendar.YEAR,year)
//                    myCalendar.set(Calendar.MONTH,month)
//                    myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
//
            }

            binding.root.setOnClickListener {
                onClickAlarmListener?.invoke(alarmData)
                if (countClick > 0) {
                    binding.btnOn.rotation = -90F
                    binding.expandedLayou.toggle()
                    countClick--
                } else if (countClick == 0) {
                    binding.btnOn.rotation = 90F
                    binding.expandedLayou.toggle()
                    countClick++
                }


            }

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemAlarmBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = models.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }

    private var btnOnClick: ((AlarmData) -> Unit) = { _ -> }
    fun tbnOnCLickListener(btnOnClick: (AlarmData) -> Unit) {
        this.btnOnClick = btnOnClick
    }

    private var tvOnClick: ((AlarmData) -> Unit) = { _ -> }
    fun tbnOnCLickTimer(tvOnClick: (AlarmData) -> Unit) {
        this.tvOnClick = tvOnClick
    }

    private var ivAdd: ((AlarmData) -> Unit) = { _ -> }
    fun addOnClick(ivAdd: (AlarmData) -> Unit) {
        this.ivAdd = ivAdd
    }

    private var ivData: ((AlarmData,TextView) -> Unit) = { _ ,_-> }
    fun ivDateClick(ivDate: (AlarmData,TextView) -> Unit) {
        this.ivData = ivDate
    }




    private fun onClick(binding: ItemAlarmBinding) {
        binding.btnOn.setOnClickListener {

            if (countClick > 0) {
                binding.btnOn.rotation = -90F
                binding.expandedLayou.toggle()
                countClick--
            } else if (countClick == 0) {
                binding.btnOn.rotation = 90F
                binding.expandedLayou.toggle()
                countClick++
            }


        }
    }


}
