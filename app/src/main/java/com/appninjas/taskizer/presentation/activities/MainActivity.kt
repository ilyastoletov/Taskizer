package com.appninjas.taskizer.presentation.activities

import android.content.DialogInterface
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.appninjas.domain.model.Task
import com.appninjas.taskizer.databinding.ActivityMainBinding
import com.appninjas.taskizer.presentation.adapters.TaskAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModel<MainViewModel>()

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
        updateTasksRv()
        binding.addTaskBtn.setOnClickListener {
            newTaskDialog()
        }

    }

    private fun updateTasksRv() {
        viewModel.getTasksList()
        viewModel.taskList.observe(this@MainActivity) {tasks ->
            val taskAdapter = TaskAdapter(tasks)
            binding.tasksMainRv.apply {
                adapter = taskAdapter
                layoutManager = LinearLayoutManager(context)
            }
        }
    }

    private fun newTaskDialog() {
        val newTaskEditText = EditText(this@MainActivity)
        newTaskEditText.hint = "Введите текст задачи"
        val dialogButtonListener = DialogInterface.OnClickListener { dialog, element ->
            when(element) {
                DialogInterface.BUTTON_POSITIVE -> {
                    val taskFieldText = newTaskEditText.text.toString()
                    if (taskFieldText.isNotEmpty()) {
                        viewModel.saveTask(Task(taskDescription = taskFieldText))
                        updateTasksRv()
                    } else {
                        Toast.makeText(this@MainActivity, "Ошибка. Отсутствует текст задачи", Toast.LENGTH_SHORT).show()
                    }
                }
                DialogInterface.BUTTON_NEGATIVE -> dialog.cancel()
            }
        }

        val dialog = AlertDialog.Builder(this@MainActivity)
            .setTitle("Новая задача")
            .setView(newTaskEditText)
            .setPositiveButton("Добавить", dialogButtonListener)
            .setNegativeButton("Отмена", dialogButtonListener)
            .setCancelable(true)

        dialog.show()
    }

    private fun getTime(time: String): String = when(time.toInt()) {
        in 6..12 -> "Доброе утро"
        in 12..18 -> "Добрый день"
        in 18..23 -> "Добрый вечер"
        in 0..6 -> "Доброй ночи"
        else -> "Приятного дня"
    }

}