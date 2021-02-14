package com.example.android_kotlin.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.example.android_kotlin.R
import com.example.android_kotlin.data.model.Note
import com.example.android_kotlin.databinding.ActivityMainBinding
import com.example.android_kotlin.ui.LogoutDialog
import com.example.android_kotlin.ui.vieewModel.MainViewModel
import com.firebase.ui.auth.AuthUI
import org.koin.android.viewmodel.ext.android.viewModel


class MainActivity() : BaseActivity<List<Note>?>(), LogoutDialog.LogoutListener {

    override val viewModel: MainViewModel by viewModel()
    override val layoutRes: Int = R.layout.activity_main
    private lateinit var adapter: MainAdapter
    override val ui: ActivityMainBinding
            by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(ui.toolbar)

        adapter = MainAdapter(object : OnItemOnClickListener {
            override fun onItemClick(note: Note) {
                openNoteScreen(note)
            }
        })
        ui.mainRecycler.adapter = adapter
        ui.fab.setOnClickListener { openNoteScreen(null) }
    }

    override fun renderData(data: List<Note>?) {
        if (data == null) return
        adapter.notes = data
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean =
        MenuInflater(this).inflate(R.menu.menu_main, menu).let { true }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when (item.itemId) {
            R.id.logout -> showLogoutDialog().let { true }
            else -> false
        }

    private fun showLogoutDialog() {
        supportFragmentManager.findFragmentByTag(LogoutDialog.TAG) ?: LogoutDialog.createInstance()
            .show(supportFragmentManager, LogoutDialog.TAG)
    }

    override fun onLogout() {
        AuthUI.getInstance()
            .signOut(this)
            .addOnCompleteListener {
                startActivity(Intent(this, SplashActivity::class.java))
                finish()
            }
    }

    private fun openNoteScreen(note: Note? = null) {
        startActivity(NoteActivity.getStartIntent(this, note?.id))
    }

    companion object {
        fun getStartIntent(context: Context) = Intent(context, MainActivity::class.java)
    }
}