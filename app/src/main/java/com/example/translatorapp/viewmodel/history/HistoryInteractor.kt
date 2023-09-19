package com.example.translatorapp.viewmodel.history

import com.example.model.data.DataModel
import com.example.core.viewmodel.Interactor
import com.example.model.AppState

class HistoryInteractor(
    private val repositoryRemote: com.example.repository.Repository<List<DataModel>>,
    private val repositoryLocal: com.example.repository.RepositoryLocal<List<DataModel>>
) : com.example.core.viewmodel.Interactor<AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): com.example.model.AppState {
        return com.example.model.AppState.Success(
            if (fromRemoteSource) {
                repositoryRemote
            } else {
                repositoryLocal
            }.getData(word)
        )
    }
}