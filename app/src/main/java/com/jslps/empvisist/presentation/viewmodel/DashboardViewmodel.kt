package com.jslps.empvisist.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.jslps.empvisist.data.local.entites.tblClusterList
import com.jslps.empvisist.data.local.entites.tblLogin
import com.jslps.empvisist.data.local.entites.tblVillageList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class DashboardViewmodel @Inject constructor(
    private val panchayatVM: PanchayatViewModel,
    private val userVM: UserViewModel,
    private val villageVM: VillageViewModel
) : ViewModel() {
    val arrayPanchayatList: StateFlow<List<tblClusterList>> = panchayatVM.arrayPanchayatList
    fun getPanchayatList(){
        panchayatVM.getPanchayatList()
    }
    val panchayat: StateFlow<tblClusterList?> = panchayatVM.panchayat
    fun getPanchayatById(panchayatId: String) {
        panchayatVM.getPanchayatById(panchayatId)
    }

    val useDetails: StateFlow<tblLogin> = userVM.arrayUseList
    fun getUserDetail(){
        userVM.getUserDetailsList()
    }

    val villageList:StateFlow<List<tblVillageList>> = villageVM.arrayVillageList
    fun getVillageList(clusterCode:String){
        villageVM.getVillageList(clusterCode)

    }
}