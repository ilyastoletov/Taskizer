package com.appninjas.taskizer.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.appninjas.domain.model.Task
import com.appninjas.taskizer.databinding.TaskItemBinding

class TaskAdapter(private var tasksList: List<Task>,
                  private val listener: TaskEditListener): RecyclerView.Adapter<TaskAdapter.Holder>() {

    inner class Holder(private val binding: TaskItemBinding, itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(model: Task) {
            with(binding) {
                taskDescriptionText.text = model.taskDescription
                editTaskBtn.setOnClickListener {
                    listener.onClick(model)
                }
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

    fun changeList(newList: List<Task>) {
        tasksList = newList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = tasksList.size

    interface TaskEditListener {
        fun onClick(model: Task)
    }

}