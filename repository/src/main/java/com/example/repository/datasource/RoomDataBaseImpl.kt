package com.example.repository.datasource

import com.example.model.data.DataModel
import com.example.repository.convertDataModelSuccessToEntity
import com.example.repository.mapHistoryEntityToSearchResult

class RoomDataBaseImpl(private val historyDao: com.example.repository.room.HistoryDao) :
    DataSourceLocal<List<DataModel>> {
    override suspend fun saveToDB(appState: com.example.model.AppState) {
        convertDataModelSuccessToEntity(appState)?.let {
            historyDao.insert(it)
        }
    }

    override suspend fun getData(word: String): List<com.example.model.data.DataModel> {
        return mapHistoryEntityToSearchResult(historyDao.all())
    }
}