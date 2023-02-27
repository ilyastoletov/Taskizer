package com.appninjas.taskizer.presentation.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.appninjas.domain.model.Task
import com.appninjas.taskizer.databinding.ActivityMainBinding
import com.appninjas.taskizer.presentation.adapters.TaskAdapter
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUI()
    }

    private fun setupUI() {
        val sdf = SimpleDateFormat("HH")
        val currentTime = sdf.format(Date())
        binding.greetingText.text = getTime(currentTime)

        val testTaskList: List<Task> = listOf(
            Task("Погладить кота"),
            Task("Погладить бельё"),
            Task("Развесить бельё"),
            Task("Я хочу составить длинное описание тестовой задачи, но понятия не имею что даже писать"),
            Task("Развеселить и одновременно удовлетворить себя мастурбацией, убить двух зайцев одним выстрелом, так скажем-с"),
            Task("Думаю этого достаточно"),
        )
        val taskAdapter = TaskAdapter(testTaskList)
        binding.tasksMainRv.apply {
            adapter = taskAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun getTime(time: String): String = when(time.toInt()) {
        in 6..12 -> "Доброе утро"
        in 12..18 -> "Добрый день"
        in 18..23 -> "Добрый вечер"
        in 0..6 -> "Доброй ночи"
        else -> "Приятного дня"
    }

}