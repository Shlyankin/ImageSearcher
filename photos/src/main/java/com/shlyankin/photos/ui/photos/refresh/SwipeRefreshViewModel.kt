package com.shlyankin.photos.ui.photos.refresh

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import com.shlyankin.photos.di.DefaultDispatcher
import com.shlyankin.util.utils.emitEvent
import com.shlyankin.util.utils.flow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SwipeRefreshViewModel @Inject constructor(
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _currentState = MutableSharedFlow<RefreshUiState>()
    val currentState = _currentState.buffer(
        capacity = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    private val _refreshEvent = MutableSharedFlow<Unit>()
    val refreshEvent = _refreshEvent.flow()

    fun onLoadStateChanged(loadState: CombinedLoadStates) {
        viewModelScope.launch(defaultDispatcher) {
            if (loadState.refresh is LoadState.Loading) {
                _currentState.emit(
                    RefreshUiState(
                        isRefreshing = true,
                        isError = false
                    )
                )
            } else {
                val errorState = when {
                    loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                    loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                    loadState.refresh is LoadState.Error -> {
                        loadState.refresh as LoadState.Error
                    }
                    else -> null
                }
                _currentState.emit(
                    RefreshUiState(
                        false,
                        errorState != null
                    )
                )
            }
        }
    }

    fun onSwipeRefresh() {
        viewModelScope.launch(defaultDispatcher) {
            _refreshEvent.emitEvent()
        }
    }
}