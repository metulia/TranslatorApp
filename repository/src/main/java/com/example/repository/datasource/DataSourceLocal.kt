package com.example.repository.datasource

interface DataSourceLocal <T> : DataSource<T> {
    suspend fun saveToDB(appState: com.example.model.AppState)
}