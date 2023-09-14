package com.example.translatorapp.model.repository

import com.example.translatorapp.model.AppState
import com.example.translatorapp.model.data.DataModel
import com.example.translatorapp.model.datasource.DataSourceLocal

class RepositoryLocalImpl (private val dataSource: DataSourceLocal<List<DataModel>>) :
    RepositoryLocal<List<DataModel>> {

    override suspend fun saveToDB(appState: AppState) {
        dataSource.saveToDB(appState)
    }

    override suspend fun getData(word: String): List<DataModel> {
        return dataSource.getData(word)
    }
}