package com.example.translatorapp.model.datasource

import com.example.translatorapp.model.AppState
import com.example.translatorapp.model.data.DataModel
import com.example.translatorapp.model.room.HistoryDao
import com.example.translatorapp.utils.ui.convertDataModelSuccessToEntity
import com.example.translatorapp.utils.ui.mapHistoryEntityToSearchResult

class RoomDataBaseImpl(private val historyDao: HistoryDao) : DataSourceLocal<List<DataModel>> {
    override suspend fun saveToDB(appState: AppState) {
        convertDataModelSuccessToEntity(appState)?.let {
            historyDao.insert(it)
        }
    }

    override suspend fun getData(word: String): List<DataModel> {
        return mapHistoryEntityToSearchResult(historyDao.all())
    }
}