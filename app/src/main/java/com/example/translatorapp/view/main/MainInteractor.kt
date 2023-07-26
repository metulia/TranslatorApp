package com.example.translatorapp.view.main

import com.example.translatorapp.model.data.AppState
import com.example.translatorapp.model.data.DataModel
import com.example.translatorapp.model.repository.Repository
import com.example.translatorapp.presenter.Interactor
import io.reactivex.rxjava3.core.Observable


class MainInteractor(
    private val remoteRepository: Repository<List<DataModel>>,
    private val localRepository: Repository<List<DataModel>>
) : Interactor<AppState> {

    override fun getData(word: String, fromRemoteSource: Boolean): Observable<AppState> {
        return if (fromRemoteSource) {
            remoteRepository.getData(word).map { AppState.Success(it) }
        } else {
            localRepository.getData(word).map { AppState.Success(it) }
        }
    }
}