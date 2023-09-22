package com.example.translatorapp.view.history

import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.core.BaseActivity
import com.example.model.AppState
import com.example.model.data.DataModel
import com.example.model.dto.SearchResultDto
import com.example.translatorapp.databinding.ActivityHistoryBinding
import com.example.translatorapp.viewmodel.history.HistoryInteractor
import com.example.translatorapp.viewmodel.history.HistoryViewModel
import org.koin.android.ext.android.inject

class HistoryActivity : BaseActivity<AppState, HistoryInteractor>() {
    private lateinit var binding: ActivityHistoryBinding
    override lateinit var model: HistoryViewModel
    private val adapter: HistoryAdapter by lazy { HistoryAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        iniViewModel()
        initViews()
    }

    override fun onResume() {
        super.onResume()
        model.getData("", false)
    }

    override fun setDataToAdapter(data: List<DataModel>) {
        adapter.setData(data)
    }

    private fun iniViewModel() {
        if (binding.historyActivityRecyclerview.adapter != null) {
            throw IllegalStateException("The ViewModel should be initialised first")
        }
        val viewModel: HistoryViewModel by inject()
        model = viewModel
        model.subscribe()
            .observe(this@HistoryActivity, Observer<com.example.model.AppState> { renderData(it) })
    }

    private fun initViews() {
        binding.historyActivityRecyclerview.adapter = adapter
    }
}