package com.example.translatorapp.viewmodel.main

import com.example.translatorapp.model.AppState
import com.example.translatorapp.model.data.DataModel
import com.example.translatorapp.model.repository.Repository
import com.example.translatorapp.model.repository.RepositoryLocal
import com.example.translatorapp.viewmodel.Interactor

class MainInteractor(
    private val repositoryRemote: Repository<List<DataModel>>,
    private val repositoryLocal: RepositoryLocal<List<DataModel>>
) : Interactor<AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        val appState: AppState

        if (fromRemoteSource) {
            appState = AppState.Success(repositoryRemote.getData(word))
            repositoryLocal.saveToDB(appState)
        } else {
            appState = AppState.Success(repositoryLocal.getData(word))
        }

        return appState
    }
}