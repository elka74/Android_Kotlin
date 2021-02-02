package com.example.android_kotlin.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.example.android_kotlin.R
import com.example.android_kotlin.data.model.NoAuthException
import com.example.android_kotlin.databinding.ActivityMainBinding
import com.example.android_kotlin.databinding.ActivitySplashBinding
import com.example.android_kotlin.ui.main.NoteActivity.Companion.getStartIntent
import com.google.android.material.snackbar.Snackbar
import java.util.logging.Handler


private const val START_DELAY = 1000L

class SplashActivity : BaseActivity<Boolean?, SplashViewState>() {

    override val viewModel: SplashViewModel by lazy {
        ViewModelProviders.of(this).get(SplashViewModel::class.java)
    }

    override val ui: ActivitySplashBinding
            by lazy { ActivitySplashBinding.inflate(layoutInflater) }

    override val layoutRes: Int = R.layout.activity_splash


    override fun onResume() {
        super.onResume()
        Handler().postDelayed({ viewModel.requestUser() }, START_DELAY)
    }

    override fun renderData(data: Boolean?) {
        data?.takeIf { it }?.let {
            startMainActivity()
        }
    }

    override fun renderError(error: Throwable) = when (error) {
        is NoAuthException -> startLoginActivity()
        else -> error.message?.let { showError(it) }
    }



    private fun startLoginActivity(){
        srartActivityForResult(
            AuthUI.getInstanse())
            .createSingnInIntetnBuilder()
    }


    private fun startMainActivity() {
        startActivity(MainActivity.getStartIntent(this))
        finish()
    }
}
