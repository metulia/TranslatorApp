package com.example.translatorapp.model.repository

import com.example.translatorapp.model.data.DataModel
import io.reactivex.rxjava3.core.Observable


interface Repository<T> {
    fun getData(word: String): Observable<List<DataModel>>
}