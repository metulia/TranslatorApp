package com.example.translatorapp.model.repository

import com.example.translatorapp.model.AppState

interface RepositoryLocal <T> : Repository<T> {

    suspend fun saveToDB(appState: AppState)
}