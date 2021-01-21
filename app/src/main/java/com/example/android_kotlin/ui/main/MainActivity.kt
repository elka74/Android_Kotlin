package com.example.android_kotlin.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.android_kotlin.R
import com.example.android_kotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var ui:ActivityMainBinding
    lateinit var viewModel: MainViewModel
    lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = ActivityMainBinding.inflate(layoutInflater)
        setContentView(ui.root)
        setSupportActionBar(ui.toolbar)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        adapter = MainAdapter()
        ui.mainRecycler.adapter = adapter

        viewModel.viewState().observe(this, Observer<MainViewState> { t ->
            t?.let { adapter.notes = it.notes }
        })

    }
}