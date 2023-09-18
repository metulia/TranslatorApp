package com.example.translatorapp.di

import androidx.room.Room
import com.example.model.data.DataModel
import com.example.translatorapp.viewmodel.history.HistoryInteractor
import com.example.translatorapp.viewmodel.history.HistoryViewModel
import com.example.translatorapp.viewmodel.main.MainInteractor
import com.example.translatorapp.viewmodel.main.MainViewModel
import org.koin.dsl.module

val application = module {
    single { Room.databaseBuilder(get(), com.example.repository.room.HistoryDataBase::class.java, "HistoryDB").build() }
    single { get<com.example.repository.room.HistoryDataBase>().historyDao() }

    single<com.example.repository.Repository<List<DataModel>>> {
        com.example.repository.RepositoryImpl(
            com.example.repository.datasource.RetrofitImpl()
        )
    }
    single<com.example.repository.RepositoryLocal<List<DataModel>>> {
        com.example.repository.RepositoryLocalImpl(
            com.example.repository.datasource.RoomDataBaseImpl(get())
        )
    }
}

val mainScreen = module {
    factory { MainInteractor(get(), get()) }
    factory { MainViewModel(get()) }
}

val historyScreen = module {
    factory { HistoryViewModel(get()) }
    factory { HistoryInteractor(get(), get()) }
}