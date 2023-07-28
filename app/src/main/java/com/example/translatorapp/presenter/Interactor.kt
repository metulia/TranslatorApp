package com.example.translatorapp.presenter

import com.example.translatorapp.model.data.AppState
import io.reactivex.rxjava3.core.Observable


interface Interactor<T> {
    fun getData(word: String, fromRemoteSource: Boolean): Observable<AppState>
}