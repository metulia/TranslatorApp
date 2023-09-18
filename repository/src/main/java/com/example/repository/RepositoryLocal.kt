package com.example.repository

interface RepositoryLocal <T> : Repository<T> {

    suspend fun saveToDB(appState: com.example.model.AppState)
}