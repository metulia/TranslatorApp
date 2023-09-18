package com.example.repository

import com.example.repository.room.HistoryEntity

fun mapHistoryEntityToSearchResult(list: List<HistoryEntity>): List<com.example.model.data.DataModel> {
    val searchResult = ArrayList<com.example.model.data.DataModel>()

    if (!list.isNullOrEmpty()) {
        for (entity in list) {
            searchResult.add(com.example.model.data.DataModel(entity.word, null))
        }
    }

    return searchResult
}

fun convertDataModelSuccessToEntity(appState: com.example.model.AppState): HistoryEntity? {
    return when (appState) {
        is com.example.model.AppState.Success -> {
            val searchResult = appState.data

            if (searchResult.isNullOrEmpty() || searchResult[0].text.isNullOrEmpty()) {
                null
            } else {
                HistoryEntity(searchResult[0].text!!, null)
            }
        }
        else -> null
    }
}

fun parseLocalSearchResults(appState: com.example.model.AppState): com.example.model.AppState {
    return com.example.model.AppState.Success(mapResult(appState, false))
}

fun parseOnlineSearchResults(appState: com.example.model.AppState): com.example.model.AppState {
    return com.example.model.AppState.Success(mapResult(appState, true))
}

private fun mapResult(
    appState: com.example.model.AppState,
    isOnline: Boolean
): List<com.example.model.data.DataModel> {
    val newSearchResults = arrayListOf<com.example.model.data.DataModel>()

    when (appState) {
        is com.example.model.AppState.Success -> {
            getSuccessResultData(appState, isOnline, newSearchResults)
        }
        else -> {
            // Nothing to do
        }
    }

    return newSearchResults
}

private fun getSuccessResultData(
    appState: com.example.model.AppState.Success,
    isOnline: Boolean,
    newDataModels: ArrayList<com.example.model.data.DataModel>
) {
    val dataModels: List<com.example.model.data.DataModel> =
        appState.data as List<com.example.model.data.DataModel>
    if (dataModels.isNotEmpty()) {
        if (isOnline) {
            for (searchResult in dataModels) {
                parseOnlineResult(searchResult, newDataModels)
            }
        } else {
            for (searchResult in dataModels) {
                newDataModels.add(
                    com.example.model.data.DataModel(
                        searchResult.text,
                        arrayListOf()
                    )
                )
            }
        }
    }
}

private fun parseOnlineResult(
    dataModel: com.example.model.data.DataModel,
    newDataModels: ArrayList<com.example.model.data.DataModel>
) {
    if (!dataModel.text.isNullOrBlank() && !dataModel.meanings.isNullOrEmpty()) {
        val newMeanings = arrayListOf<com.example.model.data.Meanings>()

        dataModel.meanings.let {
            for (meaning in dataModel.meanings!!) {
                if (meaning.translation != null && !meaning.translation!!.translation.isNullOrBlank()) {
                    newMeanings.add(
                        com.example.model.data.Meanings(
                            meaning.translation,
                            meaning.imageUrl
                        )
                    )
                }
            }
        }

        if (newMeanings.isNotEmpty()) {
            newDataModels.add(com.example.model.data.DataModel(dataModel.text, newMeanings))
        }
    }
}

fun parseSearchResults(state: com.example.model.AppState): com.example.model.AppState {
    val newSearchResults = arrayListOf<com.example.model.data.DataModel>()
    when (state) {
        is com.example.model.AppState.Success -> {
            val searchResults = state.data
            if (!searchResults.isNullOrEmpty()) {
                for (searchResult in searchResults) {
                    parseResult(searchResult, newSearchResults)
                }
            }
        }

        else -> {}
    }

    return com.example.model.AppState.Success(newSearchResults)
}

private fun parseResult(
    dataModel: com.example.model.data.DataModel,
    newDataModels: ArrayList<com.example.model.data.DataModel>
) {
    if (!dataModel.text.isNullOrBlank() && !dataModel.meanings.isNullOrEmpty()) {
        val newMeanings = arrayListOf<com.example.model.data.Meanings>()
        for (meaning in dataModel.meanings!!) {
            if (meaning.translation != null && !meaning.translation!!.translation.isNullOrBlank()) {
                newMeanings.add(
                    com.example.model.data.Meanings(
                        meaning.translation,
                        meaning.imageUrl
                    )
                )
            }
        }
        if (newMeanings.isNotEmpty()) {
            newDataModels.add(com.example.model.data.DataModel(dataModel.text, newMeanings))
        }
    }
}

fun convertMeaningsToString(meanings: List<com.example.model.data.Meanings>): String {
    var meaningsSeparatedByComma = String()
    for ((index, meaning) in meanings.withIndex()) {
        meaningsSeparatedByComma += if (index + 1 != meanings.size) {
            String.format("%s%s", meaning.translation?.translation, ", ")
        } else {
            meaning.translation?.translation
        }
    }
    return meaningsSeparatedByComma
}