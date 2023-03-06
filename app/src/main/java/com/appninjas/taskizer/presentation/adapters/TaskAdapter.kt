package com.appninjas.taskizer.presentation.adapters

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.appninjas.domain.model.Task
import com.appninjas.taskizer.R
import com.appninjas.taskizer.databinding.TaskItemBinding

class TaskAdapter(val tasksList: ArrayList<Task>,
                  private val listener: TaskClickListener): RecyclerView.Adapter<TaskAdapter.Holder>() {

    inner class Holder(private val binding: TaskItemBinding, itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(model: Task) {
            println(model.taskStatus)
            if(model.taskStatus) makeTaskChecked()
            with(binding) {
                taskDescriptionText.text = model.taskDescription
                checkTaskButton.setOnClickListener {
                    makeTaskChecked()
                    listener.onTaskChecked(model)
                }
                deleteTaskBtn.setOnClickListener {
                    listener.onClickDelete(model)
                    binding.root.visibility = View.GONE
                }
            }
        }

        private fun makeTaskChecked() {
            with(binding) {
                checkTaskButton.setImageResource(R.drawable.baseline_check_box_24)
                taskDescriptionText.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                editTaskBtn.visibility = View.GONE
                deleteTaskBtn.visibility = View.VISIBLE
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = TaskItemBinding.inflate(LayoutInflater.from(parent.context))
        return Holder(binding, binding.root)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(tasksList[position])
    }

    override fun getItemCount(): Int = tasksList.size

    interface TaskClickListener {
        fun onClickDelete(model: Task)
        fun onTaskChecked(model: Task)
    }

}