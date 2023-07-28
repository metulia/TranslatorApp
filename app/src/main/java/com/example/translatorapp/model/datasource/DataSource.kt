package com.example.translatorapp.model.datasource

import com.example.translatorapp.model.data.DataModel
import io.reactivex.rxjava3.core.Observable

interface DataSource<T> {
    fun getData(word: String): Observable<List<DataModel>>
}