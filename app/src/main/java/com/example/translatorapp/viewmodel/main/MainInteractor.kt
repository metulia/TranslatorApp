package com.example.translatorapp.viewmodel.main

import com.example.model.data.DataModel
import com.example.core.viewmodel.Interactor
import com.example.model.AppState

class MainInteractor(
    private val repositoryRemote: com.example.repository.Repository<List<DataModel>>,
    private val repositoryLocal: com.example.repository.RepositoryLocal<List<DataModel>>
) : com.example.core.viewmodel.Interactor<AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): com.example.model.AppState {
        val appState: com.example.model.AppState

        if (fromRemoteSource) {
            appState = com.example.model.AppState.Success(repositoryRemote.getData(word))
            repositoryLocal.saveToDB(appState)
        } else {
            appState = com.example.model.AppState.Success(repositoryLocal.getData(word))
        }

        return appState
    }
}