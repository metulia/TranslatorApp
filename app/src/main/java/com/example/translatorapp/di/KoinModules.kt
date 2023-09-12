package com.example.translatorapp.di

import androidx.room.Room
import com.example.translatorapp.model.data.DataModel
import com.example.translatorapp.model.datasource.RetrofitImpl
import com.example.translatorapp.model.datasource.RoomDataBaseImpl
import com.example.translatorapp.model.repository.Repository
import com.example.translatorapp.model.repository.RepositoryImpl
import com.example.translatorapp.model.repository.RepositoryLocal
import com.example.translatorapp.model.repository.RepositoryLocalImpl
import com.example.translatorapp.model.room.HistoryDataBase
import com.example.translatorapp.viewmodel.history.HistoryInteractor
import com.example.translatorapp.viewmodel.history.HistoryViewModel
import com.example.translatorapp.viewmodel.main.MainInteractor
import com.example.translatorapp.viewmodel.main.MainViewModel
import org.koin.dsl.module

val application = module {
    single { Room.databaseBuilder(get(), HistoryDataBase::class.java, "HistoryDB").build() }
    single { get<HistoryDataBase>().historyDao() }

    single<Repository<List<DataModel>>> { RepositoryImpl(RetrofitImpl()) }
    single<RepositoryLocal<List<DataModel>>> { RepositoryLocalImpl(RoomDataBaseImpl(get())) }
}

val mainScreen = module {
    factory { MainInteractor(get(), get()) }
    factory { MainViewModel(get()) }
}

val historyScreen = module {
    factory { HistoryViewModel(get()) }
    factory { HistoryInteractor(get(), get()) }
}