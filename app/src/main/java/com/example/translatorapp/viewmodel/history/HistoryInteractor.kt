package com.example.translatorapp.viewmodel.history

import com.example.translatorapp.model.AppState
import com.example.translatorapp.model.data.DataModel
import com.example.translatorapp.model.repository.Repository
import com.example.translatorapp.model.repository.RepositoryLocal
import com.example.translatorapp.viewmodel.Interactor

class HistoryInteractor(
    private val repositoryRemote: Repository<List<DataModel>>,
    private val repositoryLocal: RepositoryLocal<List<DataModel>>
) : Interactor<AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        return AppState.Success(
            if (fromRemoteSource) {
                repositoryRemote
            } else {
                repositoryLocal
            }.getData(word)
        )
    }
}