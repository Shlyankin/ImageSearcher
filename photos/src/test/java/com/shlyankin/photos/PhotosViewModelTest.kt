package com.shlyankin.photos

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.*
import androidx.recyclerview.widget.ListUpdateCallback
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.shlyankin.photos.ui.photos.PhotoUiState
import com.shlyankin.photos.ui.photos.PhotosViewModel
import com.shlyankin.photos.ui.photos.adapter.PhotosDiffUtil
import com.shlyankin.photos.ui.usecase.favourite.FavouriteUseCase
import com.shlyankin.photos.ui.usecase.photo.PhotosUseCase
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.powermock.modules.junit4.PowerMockRunner

@ExperimentalCoroutinesApi
@RunWith(PowerMockRunner::class)
class PhotosViewModelTest {

    companion object {
        private lateinit var viewModel: PhotosViewModel
    }

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    val coroutineRule = CoroutinesTestRule()

    private val photosUseCase: PhotosUseCase = mock()
    private val favouriteUseCase: FavouriteUseCase = mock()

    private val favouritePhotos = listOf(uiPhoto1)
    private val favouritePhotosFlow = MutableStateFlow(favouritePhotos)

    private val photos = listOf(uiPhoto1, uiPhoto2, uiPhoto3, uiPhoto4)
    private val photosFlow = MutableStateFlow(PagingData.from(photos))

    private fun createDiffer() = AsyncPagingDataDiffer(PhotosDiffUtil(), NoopListCallback())

    @Before
    fun setup() {
        coroutineRule.runBlockingTest {
            mockAll()
            favouritePhotosFlow.emit(favouritePhotos)
            photosFlow.emit(PagingData.from(photos))
            viewModel = PhotosViewModel(
                photosUseCase,
                favouriteUseCase,
                coroutineRule.testDispatcher,
                coroutineRule.testDispatcher
            )
        }
    }

    private fun mockAll() {
        coroutineRule.runBlockingTest {
            whenever(photosUseCase.photos).thenReturn(photosFlow)
            whenever(favouriteUseCase.favouritePhotos).thenReturn(favouritePhotosFlow)
            whenever(favouriteUseCase.addToFavourite(uiPhoto2)).then {
                coroutineRule.runBlockingTest {
                    favouritePhotosFlow.emit(
                        favouritePhotosFlow.value.toMutableList().apply { add(uiPhoto2) })
                }
            }
            whenever(favouriteUseCase.removeFromFavourite(uiPhoto2.id)).then {
                coroutineRule.runBlockingTest {
                    favouritePhotosFlow.emit(
                        favouritePhotosFlow.value.toMutableList().apply { remove(uiPhoto2) })
                }
            }
        }
    }

    class NoopListCallback : ListUpdateCallback {
        override fun onChanged(position: Int, count: Int, payload: Any?) {}
        override fun onMoved(fromPosition: Int, toPosition: Int) {}
        override fun onInserted(position: Int, count: Int) {}
        override fun onRemoved(position: Int, count: Int) {}
    }

    @Test
    fun initializeTest() = coroutineRule.runBlockingTest {
        viewModel.photos.first().let {
            val differ = createDiffer()
            differ.submitData(it)
            advanceUntilIdle()
            assertEquals(
                mutableListOf(uiPhoto1.copy(isFavourite = true), uiPhoto2, uiPhoto3, uiPhoto4),
                differ.snapshot().items
            )
        }
    }

    @Test
    fun testAddToFavourite() {
        coroutineRule.runBlockingTest {
            viewModel.addToFavouriteClicked(uiPhoto2)
            viewModel.photos.first().let {
                val differ = createDiffer()
                differ.submitData(it)
                advanceUntilIdle()
                val photoFromVM = differ.snapshot().items.find { it.id == uiPhoto2.id }
                assertEquals(true, photoFromVM?.isFavourite)
            }
            viewModel.addToFavouriteClicked(uiPhoto2)
            viewModel.photos.first().let {
                val differ = createDiffer()
                differ.submitData(it)
                advanceUntilIdle()
                val photoFromVM = differ.snapshot().items.find { it.id == uiPhoto2.id }
                assertEquals(false, photoFromVM?.isFavourite)
            }
        }
    }

    @Test
    fun onLoadStateChangedRefreshing() {
        coroutineRule.scope.launch {
            viewModel.currentState.collect {
                assertEquals(
                    PhotoUiState(
                        progressVisible = true,
                        retryVisible = false,
                        errorVisible = false
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
                    PhotoUiState(
                        progressVisible = false,
                        retryVisible = true,
                        errorVisible = true
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
                    PhotoUiState(
                        progressVisible = false,
                        retryVisible = false,
                        errorVisible = true
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