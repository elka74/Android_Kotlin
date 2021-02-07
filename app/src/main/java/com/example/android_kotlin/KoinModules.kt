package com.example.android_kotlin

import com.example.android_kotlin.data.model.FireStoreProvider
import com.example.android_kotlin.data.model.Repository
import com.example.android_kotlin.ui.vieewModel.MainViewModel
import com.example.android_kotlin.ui.vieewModel.NoteViewModel
import com.example.android_kotlin.ui.vieewModel.SplashViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.auth.FirebaseAuth
import com.example.android_kotlin.data.model.RemoteDadaProvider
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import org.koin.dsl.bind


val appModule = module {
    single { FirebaseAuth.getInstance() }
    single { FirebaseFirestore.getInstance() }
    single { FireStoreProvider(get(), get()) } bind RemoteDadaProvider::class
    single {
        Repository(get())
    }
}
val splashModule = module {
    viewModel { SplashViewModel(get()) }
}

val mainModule = module {
    viewModel { MainViewModel(get()) }
}

val noteModule = module {
    viewModel { NoteViewModel(get()) }
}
