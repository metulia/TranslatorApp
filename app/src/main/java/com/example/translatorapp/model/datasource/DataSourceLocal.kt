package com.example.translatorapp.model.datasource

import com.example.translatorapp.model.AppState

interface DataSourceLocal <T> : DataSource<T> {
    suspend fun saveToDB(appState: AppState)
}