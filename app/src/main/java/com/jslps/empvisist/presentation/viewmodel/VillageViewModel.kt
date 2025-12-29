package com.jslps.empvisist.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jslps.empvisist.data.local.entites.tblClusterList
import com.jslps.empvisist.data.local.entites.tblVillageList
import com.jslps.empvisist.domain.usecase.VillageUseCase
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class VillageViewModel @Inject constructor(
    private val villageUseCase: VillageUseCase
) : ViewModel() {
    private val _arrayVillageList = MutableStateFlow<List<tblVillageList>>(emptyList())
    val arrayVillageList: StateFlow<List<tblVillageList>> = _arrayVillageList
    fun getVillageList(clusterCode:String) {
        viewModelScope.launch(Dispatchers.IO) {
            villageUseCase.getVillageList(clusterCode).collect { villageList ->
                _arrayVillageList.value = villageList
            }

        }
    }

}