package com.example.translatorapp.viewmodel.history

import com.example.model.dto.SearchResultDto
import com.example.model.AppState
import com.example.repository.mapSearchResultToResult

class HistoryInteractor(
    private val repositoryRemote: com.example.repository.Repository<List<SearchResultDto>>,
    private val repositoryLocal: com.example.repository.RepositoryLocal<List<SearchResultDto>>
) : com.example.core.viewmodel.Interactor<AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        return AppState.Success(
            mapSearchResultToResult(
                if (fromRemoteSource) {
                    repositoryRemote
                } else {
                    repositoryLocal
                }.getData(word)
            )
        )
    }
}