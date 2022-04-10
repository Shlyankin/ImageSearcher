package com.shlyankin.photos.ui.photos.refresh

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import com.hadilq.liveevent.LiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SwipeRefreshViewModel @Inject constructor() : ViewModel() {

    private val _currentState = MutableLiveData<RefreshUiState>()
    val currentState: LiveData<RefreshUiState> = _currentState

    private val _refreshEvent = LiveEvent<Unit>()
    val refreshEvent: LiveData<Unit> = _refreshEvent

    fun onLoadStateChanged(loadState: CombinedLoadStates) {
        if (loadState.refresh is LoadState.Loading) {
            _currentState.value = RefreshUiState(
                isRefreshing = true,
                isError = false
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
            _currentState.value = RefreshUiState(
                false,
                errorState != null
            )
        }
    }

    fun onSwipeRefresh() {
        _refreshEvent.value = Unit
    }
}