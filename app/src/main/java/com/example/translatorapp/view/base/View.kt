package com.example.translatorapp.view.base

import com.example.translatorapp.model.data.AppState

interface View {
    fun renderData(appState: AppState)
}