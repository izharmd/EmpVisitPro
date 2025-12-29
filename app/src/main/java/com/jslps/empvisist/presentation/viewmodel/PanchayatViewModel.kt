package com.jslps.empvisist.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jslps.empvisist.data.local.entites.tblClusterList
import com.jslps.empvisist.domain.model.ClusterList
import com.jslps.empvisist.domain.usecase.GetPanchayatListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class PanchayatViewModel @Inject constructor(
    val getPanchayatListUseCase: GetPanchayatListUseCase
) : ViewModel() {
    private val _arrayPanchayatList = MutableStateFlow<List<tblClusterList>>(emptyList())
    val arrayPanchayatList: StateFlow<List<tblClusterList>> = _arrayPanchayatList
    fun getPanchayatList() {
        viewModelScope.launch(Dispatchers.IO) {
            getPanchayatListUseCase().collect { favoritesList ->
                _arrayPanchayatList.value = favoritesList
            }
        }
    }

    val _panchayat = MutableStateFlow<tblClusterList?>(null)
    val panchayat: StateFlow<tblClusterList?> = _panchayat

    fun getPanchayatById(panchayatId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            getPanchayatListUseCase.getPanchayatById(panchayatId).collect { panchayat ->
                _panchayat.value = panchayat
            }
        }
    }


}