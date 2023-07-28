package com.example.translatorapp.model.repository

import com.example.translatorapp.model.data.DataModel
import com.example.translatorapp.model.datasource.DataSource
import io.reactivex.rxjava3.core.Observable


class RepositoryImpl(private val dataSource: DataSource<List<DataModel>>) :
    Repository<List<DataModel>> {

    override fun getData(word: String): Observable<List<DataModel>> {
        return dataSource.getData(word)
    }
}