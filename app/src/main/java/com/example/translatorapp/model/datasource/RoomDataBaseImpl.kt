package com.example.translatorapp.model.datasource

import com.example.translatorapp.model.data.DataModel
import io.reactivex.rxjava3.core.Observable


class RoomDataBaseImpl : DataSource<List<DataModel>> {

    override fun getData(word: String): Observable<List<DataModel>> {
        TODO("not implemented") // To change body of created functions use File
// | Settings | File Templates.
    }
}