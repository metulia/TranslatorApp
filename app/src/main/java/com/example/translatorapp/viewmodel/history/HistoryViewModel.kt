package com.example.translatorapp.viewmodel.history

import androidx.lifecycle.LiveData
import com.example.repository.parseLocalSearchResults
import com.example.core.viewmodel.BaseViewModel
import com.example.model.AppState
import kotlinx.coroutines.launch

class HistoryViewModel(private val interactor: HistoryInteractor) :
    com.example.core.viewmodel.BaseViewModel<AppState>() {

    private val liveDataForViewToObserve: LiveData<com.example.model.AppState> = _mutableLiveData

    override fun onCleared() {
        _mutableLiveData.value = com.example.model.AppState.Success(null)
        super.onCleared()
    }

    fun subscribe(): LiveData<com.example.model.AppState> {
        return liveDataForViewToObserve
    }

    override fun getData(word: String, isOnline: Boolean) {
        _mutableLiveData.value = com.example.model.AppState.Loading(null)
        cancelJob()
        viewModelCoroutineScope.launch { startInteractor(word, isOnline) }

    }

    private suspend fun startInteractor(word: String, isOnline: Boolean) {
        _mutableLiveData.postValue(parseLocalSearchResults(interactor.getData(word, isOnline)))
    }

    override fun handleError(error: Throwable) {
        _mutableLiveData.postValue(com.example.model.AppState.Error(error))
    }
}