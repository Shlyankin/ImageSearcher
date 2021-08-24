package com.shlyankin.imagesearcher.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shlyankin.imagesearcher.R
import com.shlyankin.navigation.AppScreen
import com.shlyankin.navigation.events.NavigationEventListener
import com.shlyankin.util.navigation.NavigationInfo
import com.shlyankin.util.navigation.NavigationViewModel
import com.shlyankin.util.utils.flow
import com.shlyankin.view_photo.ui.view_photo.ViewPhotoFragment
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainNavigationViewModel @Inject constructor(
    navigationEventListener: NavigationEventListener
) : ViewModel(), NavigationViewModel {
    private val _navigateEvent = MutableSharedFlow<NavigationInfo>()
    override val navigateEvent = _navigateEvent.flow()

    init {
        viewModelScope.launch {
            navigationEventListener.navigationEvent.collect {
                when (it) {
                    is AppScreen.ViewPhoto -> {
                        _navigateEvent.emit(
                            NavigationInfo.NavigationTo(
                                R.id.to_view_photo,
                                ViewPhotoFragment.getBundle(it.photoId)
                            )
                        )
                    }
                }
            }
        }
    }
}