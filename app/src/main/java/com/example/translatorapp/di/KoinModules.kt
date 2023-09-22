package com.example.translatorapp.di

import androidx.room.Room
import com.example.model.dto.SearchResultDto
import com.example.translatorapp.view.history.HistoryActivity
import com.example.translatorapp.view.main.MainActivity
import com.example.translatorapp.viewmodel.history.HistoryInteractor
import com.example.translatorapp.viewmodel.history.HistoryViewModel
import com.example.translatorapp.viewmodel.main.MainInteractor
import com.example.translatorapp.viewmodel.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val application = module {
    single {
        Room.databaseBuilder(
            get(),
            com.example.repository.room.HistoryDataBase::class.java,
            "HistoryDB"
        ).build()
    }
    single { get<com.example.repository.room.HistoryDataBase>().historyDao() }

    single<com.example.repository.Repository<List<SearchResultDto>>> {
        com.example.repository.RepositoryImpl(
            com.example.repository.datasource.RetrofitImpl()
        )
    }
    single<com.example.repository.RepositoryLocal<List<SearchResultDto>>> {
        com.example.repository.RepositoryLocalImpl(
            com.example.repository.datasource.RoomDataBaseImpl(get())
        )
    }
}

val mainScreen = module {
    scope(named<MainActivity>()) {
        scoped { MainInteractor(get(), get()) }
        viewModel { MainViewModel(get()) }
    }
}

val historyScreen = module {
    scope(named<HistoryActivity>()) {
        scoped { HistoryInteractor(get(), get()) }
        viewModel { HistoryViewModel(get()) }
    }
}