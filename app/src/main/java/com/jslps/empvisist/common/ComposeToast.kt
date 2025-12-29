package com.jslps.empvisist.common

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object ComposeToast {
    private val toastState = MutableStateFlow<String?>(null)

    fun show(message: String) {
        toastState.value = message
    }

    fun hide() {
        toastState.value = null
    }

    fun state(): StateFlow<String?> = toastState
}
