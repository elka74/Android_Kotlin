package com.example.android_kotlin.ui.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.viewbinding.ViewBinding
import com.example.android_kotlin.R
import com.google.android.material.snackbar.Snackbar



abstract class BaseActivity<T, VS : BaseViewState<T>> : AppCompatActivity() {

    abstract val viewModel: BaseViewModel<T, VS>
    abstract val layoutRes: Int
    abstract val ui: ViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ui.root)


        viewModel.getViewState().observe(this) {t ->
             t?.apply {
                 data?.let{data -> renderData(data)}
                 error?.let {error -> renderError(error) }
             }
        }
    }

    abstract fun renderData(data: T)

    protected open fun renderError(error: Throwable) {
        error.message?.let{showError(it)}
    }


    protected open fun showError(error: String) {
        Snackbar.make(ui.root,error, Snackbar.LENGTH_INDEFINITE).apply {
            setAction(R.string.ok_bth_title) { dismiss() }
            show()
        }

    }

}