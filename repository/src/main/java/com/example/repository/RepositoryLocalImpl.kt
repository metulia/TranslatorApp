package com.example.repository

import com.example.model.data.DataModel
import com.example.repository.datasource.DataSourceLocal

class RepositoryLocalImpl (private val dataSource: DataSourceLocal<List<DataModel>>) :
    RepositoryLocal<List<DataModel>> {

    override suspend fun saveToDB(appState: com.example.model.AppState) {
        dataSource.saveToDB(appState)
    }

    override suspend fun getData(word: String): List<com.example.model.data.DataModel> {
        return dataSource.getData(word)
    }
}