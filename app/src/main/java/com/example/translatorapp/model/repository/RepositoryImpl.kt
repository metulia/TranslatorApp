package com.example.translatorapp.model.repository

import com.example.translatorapp.model.data.DataModel
import com.example.translatorapp.model.datasource.DataSource

class RepositoryImpl(private val dataSource: DataSource<List<DataModel>>) :
    Repository<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        return dataSource.getData(word)
    }
}