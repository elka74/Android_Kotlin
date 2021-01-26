package com.example.android_kotlin.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer


abstract class BaseActivity<T, VS : BaseViewState<T>> : AppCompatActivity() {

    abstract val viewModel: BaseViewModel<T, VS>
    abstract val layoutRes: Int
    //abstract val ui: ViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutRes)


        viewModel.getViewState().observe(this, object : Observer<VS> {
            override fun onChanged(t: VS?) {
                if (t == null) return
                if (t.data != null) renderData(t.data)
                t.error?.let { renderError(it) }
            }
        })
    }

    abstract fun renderData(data: T)

    protected fun renderError(error: Throwable) {
        if (error.message != null) showError(error.message!!)
    }


    protected fun showError(error: String) {
//        val snackbar = Snackbar.make(ui.root,error, Snackbar.LENGTH_INDEFINITE)
//        snackbar.setAction(R.string.ok_bth_title, View.OnClickListener { snackbar.dismiss() })
//        snackbar.show()
    }

}