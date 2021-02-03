package com.example.android_kotlin.ui.splash

import android.app.Activity
import android.content.Intent
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.ViewModelProviders
import com.example.android_kotlin.R
import com.example.android_kotlin.data.model.NoAuthException
import com.example.android_kotlin.databinding.ActivitySplashBinding
import com.example.android_kotlin.ui.base.BaseActivity
import com.example.android_kotlin.ui.main.MainActivity
import com.example.android_kotlin.ui.note.NoteActivity.Companion.getStartIntent
import com.firebase.ui.auth.AuthUI



private const val RS_SIGN_IN = 42
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
        Handler(Looper.getMainLooper())
            .postDelayed({ viewModel.requestUser() },
                START_DELAY)
    }

    override fun renderData(data: Boolean?) {
        data?.takeIf { it }?.let { startMainActivity() }
    }

    override fun renderError(error: Throwable) {
        when (error) {
            is NoAuthException -> startLoginActivity()
            else -> error.message?.let { showError(it) }
        }
    }

    private fun startLoginActivity() {
        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setLogo(R.drawable.splash)
                .setTheme(R.style.LoginStyle)
                .setAvailableProviders(
                    listOf(
                        AuthUI.IdpConfig.EmailBuilder().build(),
                        AuthUI.IdpConfig.GoogleBuilder().build()
                    )
                )
                .build(),
            RS_SIGN_IN
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RS_SIGN_IN && resultCode != Activity.RESULT_OK)
            finish()
    }

    private fun startMainActivity() {
        startActivity(MainActivity.getStartIntent(this))
        finish()
    }
}
