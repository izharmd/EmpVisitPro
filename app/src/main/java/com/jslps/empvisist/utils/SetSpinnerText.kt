package com.jslps.birsabasistha.utils

import android.widget.Spinner

class SetSpinnerText(spin: Spinner, text: String) {
    init {
        for (i in 0 until spin.adapter.count) {
            if (spin.adapter.getItem(i).toString() == text) {
                 spin.setSelection(i)

            }
        }
    }
}