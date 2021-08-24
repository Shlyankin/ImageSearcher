package com.shlyankin.photos

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.LoadStates
import com.shlyankin.photos.ui.photos.refresh.RefreshUiState
import com.shlyankin.photos.ui.photos.refresh.SwipeRefreshViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class SwipeRefreshViewModelTest {

    companion object {
        private lateinit var viewModel: SwipeRefreshViewModel
    }

    @Before
    fun setup() {
        coroutineRule.runBlockingTest {
            viewModel = SwipeRefreshViewModel(
                coroutineRule.testDispatcher
            )
        }
    }

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    val coroutineRule = CoroutinesTestRule()

    @Test
    fun onLoadStateChangedRefreshing() {
        coroutineRule.scope.launch {
            viewModel.currentState.collect {
                assertEquals(
                    RefreshUiState(
                        isRefreshing = true,
                        isError = false
                    ), it
                )
            }
            cancel()
        }
        coroutineRule.runBlockingTest {
            viewModel.onLoadStateChanged(
                CombinedLoadStates(
                    LoadState.Loading,
                    LoadState.NotLoading(false),
                    LoadState.NotLoading(false),
                    LoadStates(
                        LoadState.NotLoading(false),
                        LoadState.NotLoading(false),
                        LoadState.NotLoading(false)
                    )
                )
            )
        }
    }

    @Test
    fun onLoadStateChangedLoadingRefreshingError() {
        coroutineRule.scope.launch {
            viewModel.currentState.collect {
                assertEquals(
                    RefreshUiState(
                        isRefreshing = false,
                        isError = true
                    ), it
                )
                cancel()
            }
        }
        coroutineRule.runBlockingTest {
            viewModel.onLoadStateChanged(
                CombinedLoadStates(
                    LoadState.Error(Exception()),
                    LoadState.NotLoading(false),
                    LoadState.NotLoading(false),
                    LoadStates(
                        LoadState.NotLoading(false),
                        LoadState.NotLoading(false),
                        LoadState.NotLoading(false)
                    )
                )
            )
        }
    }

    @Test
    fun onLoadStateChangedLoadingOtherError() {
        coroutineRule.scope.launch {
            viewModel.currentState.collect {
                assertEquals(
                    RefreshUiState(
                        isRefreshing = false,
                        isError = true
                    ), it
                )
                cancel()
            }
        }
        coroutineRule.runBlockingTest {
            viewModel.onLoadStateChanged(
                CombinedLoadStates(
                    LoadState.NotLoading(false),
                    LoadState.NotLoading(false),
                    LoadState.Error(Exception()),
                    LoadStates(
                        LoadState.NotLoading(false),
                        LoadState.NotLoading(false),
                        LoadState.NotLoading(false)
                    )
                )
            )
        }
    }
}