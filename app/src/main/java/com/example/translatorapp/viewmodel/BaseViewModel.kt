package com.example.translatorapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.translatorapp.model.data.AppState
import com.example.translatorapp.rx.SchedulerProvider
import io.reactivex.rxjava3.disposables.CompositeDisposable

abstract class BaseViewModel<T : AppState>(
    protected val liveDataForViewToObserve: MutableLiveData<T> =
        MutableLiveData(),
    protected val compositeDisposable: CompositeDisposable =
        CompositeDisposable(),
    protected val schedulerProvider: SchedulerProvider = SchedulerProvider()
) : ViewModel() {

    open fun getData(word: String, isOnline: Boolean): LiveData<T> =
        liveDataForViewToObserve

    override fun onCleared() {
        compositeDisposable.clear()
    }
}
