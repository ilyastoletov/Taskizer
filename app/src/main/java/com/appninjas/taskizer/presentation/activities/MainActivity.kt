package com.appninjas.taskizer.presentation.activities

import android.content.DialogInterface
import android.os.Bundle
import kotlin.random.Random
import android.text.SpannableStringBuilder
import android.widget.EditText
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
        binding.addTaskBtn.setOnClickListener {
            taskInteractionDialog(editing = false, model = null)
        }
        taskAdapter = TaskAdapter(listOf(), editTaskListener)
        binding.tasksMainRv.apply {
            adapter = taskAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
        viewModel.getTasksList()
        viewModel.taskList.observe(this@MainActivity) { tasks ->
            updateTaskRv(tasks)
        }
    }

    private val editTaskListener = object : TaskAdapter.TaskEditListener {
        override fun onClick(model: Task) {
            taskInteractionDialog(editing = true, defaultText = model.taskDescription, model = model)
        }
    }


    private fun updateTaskRv(tasks: List<Task>) {
        taskAdapter.changeList(tasks)
        taskAdapter.notifyDataSetChanged()
    }

    private fun taskInteractionDialog(editing: Boolean = false, defaultText: String = "", model: Task?) {
        val taskEditText = EditText(this@MainActivity)
        taskEditText.text = SpannableStringBuilder(defaultText)
        taskEditText.hint = "Введите текст задачи"
        val listener = DialogInterface.OnClickListener { dialog, element ->
            when(element) {
                DialogInterface.BUTTON_POSITIVE -> {
                    val taskFieldText = taskEditText.text.toString()
                    if (editing) {
                        viewModel.editTask(Task(
                            taskId = model!!.taskId,
                            taskDescription = taskFieldText,
                            taskStatus = model!!.taskStatus
                        ))
                    } else {
                        viewModel.saveTask(Task(
                            taskId = Random.nextInt(),
                            taskDescription = taskFieldText,
                            taskStatus = false
                        ))
                    }
                    viewModel.getTasksList()
                    viewModel.taskList.observe(this@MainActivity) {tasks ->
                        updateTaskRv(tasks)
                    }
                }
                DialogInterface.BUTTON_NEGATIVE -> dialog.cancel()
            }
        }

        val dialog = AlertDialog.Builder(this@MainActivity)
            .setTitle(when(editing) {
                true -> "Изменение задачи"
                false -> "Новая задача"
            })
            .setView(taskEditText)
            .setPositiveButton("Добавить", listener)
            .setNegativeButton("Отмена", listener)
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