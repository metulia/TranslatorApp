package com.example.translatorapp.view.base

import androidx.appcompat.app.AppCompatActivity
import com.example.translatorapp.model.data.AppState
import com.example.translatorapp.viewmodel.BaseViewModel

abstract class BaseActivity<T : AppState> : AppCompatActivity() {

    abstract val model: BaseViewModel<T>
    abstract fun renderData(appState: T)
}