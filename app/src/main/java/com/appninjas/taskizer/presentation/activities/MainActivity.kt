package com.appninjas.taskizer.presentation.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.appninjas.taskizer.databinding.ActivityMainBinding

/*
TODO(Сделать функционал создания и завершения таски)
TODO(Приветствия исходя из времени суток)
*/

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}