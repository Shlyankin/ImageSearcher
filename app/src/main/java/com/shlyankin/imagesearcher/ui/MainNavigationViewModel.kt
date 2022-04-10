package com.shlyankin.imagesearcher.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hadilq.liveevent.LiveEvent
import com.shlyankin.imagesearcher.R
import com.shlyankin.navigation.AppScreen
import com.shlyankin.navigation.events.NavigationEventListener
import com.shlyankin.util.navigation.NavigationInfo
import com.shlyankin.util.navigation.NavigationViewModel
import com.shlyankin.view_photo.ui.view_photo.ViewPhotoFragment
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainNavigationViewModel @Inject constructor(
    navigationEventListener: NavigationEventListener
) : ViewModel(), NavigationViewModel {

    private val _navigateEvent = LiveEvent<NavigationInfo>()
    override val navigateEvent: LiveData<NavigationInfo> = _navigateEvent

    init {
        viewModelScope.launch {
            navigationEventListener.navigationEvent
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext {
                    when (it) {
                        is AppScreen.ViewPhoto -> {
                            _navigateEvent.value = NavigationInfo.NavigationTo(
                                R.id.to_view_photo,
                                ViewPhotoFragment.getBundle(it.photoId)
                            )
                        }
                    }
                }.subscribe()
        }
    }
}