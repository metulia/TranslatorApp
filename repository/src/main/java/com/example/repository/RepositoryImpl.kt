package com.example.repository

import com.example.model.data.DataModel
import com.example.repository.datasource.DataSource

class RepositoryImpl(private val dataSource: DataSource<List<DataModel>>) :
    Repository<List<DataModel>> {

    override suspend fun getData(word: String): List<com.example.model.data.DataModel> {
        return dataSource.getData(word)
    }
}