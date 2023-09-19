package com.example.translatorapp.viewmodel.main

import androidx.lifecycle.LiveData
import com.example.repository.parseSearchResults
import com.example.core.viewmodel.BaseViewModel
import com.example.model.AppState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(private val interactor: MainInteractor) :
    com.example.core.viewmodel.BaseViewModel<AppState>() {

    private val liveDataForViewToObserve: LiveData<com.example.model.AppState> = _mutableLiveData

    fun subscribe(): LiveData<com.example.model.AppState> {
        return liveDataForViewToObserve
    }

    override fun getData(word: String, isOnline: Boolean) {
        _mutableLiveData.value = com.example.model.AppState.Loading(null)
        cancelJob()
        viewModelCoroutineScope.launch { startInteractor(word, isOnline) }
    }

    private suspend fun startInteractor(word: String, isOnline: Boolean) =
        withContext(Dispatchers.IO) {
            _mutableLiveData.postValue(parseSearchResults(interactor.getData(word, isOnline)))
        }

    override fun handleError(error: Throwable) {
        _mutableLiveData.postValue(com.example.model.AppState.Error(error))
    }

    override fun onCleared() {
        _mutableLiveData.value = com.example.model.AppState.Success(null)
        super.onCleared()
    }
}