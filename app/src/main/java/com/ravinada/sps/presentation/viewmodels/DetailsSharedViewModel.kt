package com.ravinada.sps.presentation.viewmodels

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
class DetailsSharedViewModel @Inject constructor() : ViewModel() {
    override fun onCleared() {
        super.onCleared()
        Timber.d("DetailsSharedViewModel onCleared")
    }
}