package com.jslps.empvisist.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jslps.empvisist.data.local.entites.tblLogin
import com.jslps.empvisist.di.IoDispatcher
import com.jslps.empvisist.domain.model.ClusterList
import com.jslps.empvisist.domain.usecase.GetUserDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject


class UserViewModel @Inject constructor(
    val getUserDetailsUseCase: GetUserDetailsUseCase,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
): ViewModel(){
    private val _arrayUseList = MutableStateFlow(tblLogin())
    val arrayUseList: StateFlow<tblLogin> = _arrayUseList
    fun getUserDetailsList() {
        viewModelScope.launch(dispatcher) {
            getUserDetailsUseCase.getUserDetails().catch { exception ->
               // Log.e("UserViewModel", exception.message ?: "Unknown error")
            }.collect { userList ->
                _arrayUseList.value = userList
            }
        }
    }
}



