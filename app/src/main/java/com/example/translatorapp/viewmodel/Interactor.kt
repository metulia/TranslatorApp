package com.example.translatorapp.viewmodel

interface Interactor <T> {
    suspend fun getData(word: String, fromRemoteSource: Boolean): T
}