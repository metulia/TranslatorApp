package com.example.translatorapp.model.datasource

import com.example.translatorapp.model.data.DataModel
import io.reactivex.rxjava3.core.Observable

class DataSourceLocal(
    private val remoteProvider: RoomDataBaseImpl =
        RoomDataBaseImpl()
) :
    DataSource<List<DataModel>> {

    override fun getData(word: String): Observable<List<DataModel>> =
        remoteProvider.getData(word)
}