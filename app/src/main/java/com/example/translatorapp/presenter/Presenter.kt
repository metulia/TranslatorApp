package com.example.translatorapp.presenter

import com.example.translatorapp.model.data.AppState
import com.example.translatorapp.view.base.View

interface Presenter<T : AppState, V : View> {
    fun attachView(view: V)
    fun detachView(view: V)
    fun getData(word: String, isOnline: Boolean)
}