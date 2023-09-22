package com.example.repository

import com.example.model.dto.SearchResultDto
import com.example.repository.datasource.DataSourceLocal

class RepositoryLocalImpl (private val dataSource: DataSourceLocal<List<SearchResultDto>>) :
    RepositoryLocal<List<SearchResultDto>> {

    override suspend fun saveToDB(appState: com.example.model.AppState) {
        dataSource.saveToDB(appState)
    }

    override suspend fun getData(word: String): List<SearchResultDto> {
        return dataSource.getData(word)
    }
}