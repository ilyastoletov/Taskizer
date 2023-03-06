package com.appninjas.taskizer.presentation.activities

import android.content.DialogInterface
import android.os.Bundle
import kotlin.random.Random
import android.text.SpannableStringBuilder
import android.util.Log
import android.view.View
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
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var taskAdapter: TaskAdapter
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
        binding.addTaskBigBtn.setOnClickListener(testAddTaskBtnListener)
        viewModel.getTasksList()
        viewModel.taskList.observe(this@MainActivity) {
            taskAdapter = TaskAdapter(it.toCollection(ArrayList()), editTaskListener)
            binding.tasksMainRv.apply {
                adapter = taskAdapter
                layoutManager = LinearLayoutManager(this@MainActivity)
            }
        }
    }

    private val testAddTaskBtnListener = View.OnClickListener {
        val taskTextValue = binding.taskTextEditText.text.toString()
        if (taskTextValue.isNotEmpty()) {
            val taskObject = Task(
                taskId = Random.nextInt(),
                taskDescription = taskTextValue,
                taskStatus = false
            )
            viewModel.saveTask(taskObject)
            taskAdapter.tasksList.add(taskObject)
            taskAdapter.notifyDataSetChanged()
        } else {
            Toast.makeText(this@MainActivity, "Введите текст задачи", Toast.LENGTH_SHORT).show()
        }
    }

    private val editTaskListener = object : TaskAdapter.TaskClickListener {
        override fun onClickDelete(model: Task) {
            viewModel.deleteTask(model)
            viewModel.getTasksList()
        }

        override fun onTaskChecked(model: Task) {
            model.taskStatus = true
            viewModel.editTask(model)
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