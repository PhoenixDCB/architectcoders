package com.dacuesta.architectcoders.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dacuesta.architectcoders.R
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.asdf()
    }
}