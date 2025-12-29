package com.jslps.empvisist.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jslps.empvisist.data.local.entites.tblLogin
import com.jslps.empvisist.domain.model.ClusterList
import com.jslps.empvisist.domain.usecase.GetUserDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


class UserViewModel @Inject constructor(
    val getUserDetailsUseCase: GetUserDetailsUseCase
): ViewModel(){
    private val _arrayUseList = MutableStateFlow(tblLogin())
    val arrayUseList: StateFlow<tblLogin> = _arrayUseList
    fun getUserDetailsList() {
        viewModelScope.launch(Dispatchers.IO) {
            getUserDetailsUseCase.getUserDetails().collect { userList ->
                _arrayUseList.value = userList
            }
        }
    }
}



