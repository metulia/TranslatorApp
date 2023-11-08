package com.example.translatorapp

import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.model.AppState
import com.example.translatorapp.viewmodel.main.MainInteractor
import com.example.translatorapp.viewmodel.main.MainViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class MainViewModelTest {

    @RunWith(AndroidJUnit4::class)
    @Config(sdk = [Build.VERSION_CODES.O_MR1])
    @ExperimentalCoroutinesApi
    class SearchViewModelTest {

        @get:Rule
        var instantExecutorRule = InstantTaskExecutorRule()

        @get:Rule
        var testCoroutineRule = TestCoroutineRule()

        private lateinit var mainViewModel: MainViewModel

        @Mock
        private lateinit var interactor: MainInteractor

        @Before
        fun setUp() {
            MockitoAnnotations.initMocks(this)
            mainViewModel = MainViewModel(interactor)
        }

        @Test
        fun coroutines_TestReturnValueIsNotNullFromRemoteTrue() {
            testCoroutineRule.runBlockingTest {
                val observer = Observer<AppState> {}
                val liveData = mainViewModel.subscribe()

                Mockito.`when`(interactor.getData(SEARCH_QUERY, true)).thenReturn(
                    AppState.Success(listOf())
                )

                try {
                    liveData.observeForever(observer)
                    mainViewModel.getData(SEARCH_QUERY, true)
                    Assert.assertNotNull(liveData.value)
                } finally {
                    liveData.removeObserver(observer)
                }
            }
        }

        @Test
        fun coroutines_TestReturnValueIsNotNullFromRemoteFalse() {
            testCoroutineRule.runBlockingTest {
                val observer = Observer<AppState> {}
                val liveData = mainViewModel.subscribe()

                Mockito.`when`(interactor.getData(SEARCH_QUERY, false)).thenReturn(
                    AppState.Success(listOf())
                )

                try {
                    liveData.observeForever(observer)
                    mainViewModel.getData(SEARCH_QUERY, false)
                    Assert.assertNotNull(liveData.value)
                } finally {
                    liveData.removeObserver(observer)
                }
            }
        }

        companion object {
            private const val SEARCH_QUERY = "some query"
        }
    }
}