package com.jslps.empvisist.data.remote.dto

import java.io.Serializable

data class VillageListDto(
    val ClfCode: String? = "",
    val PanchayatCode: String? = "",
    val LgdVillage: String? = "",
    val VillageCode: String = "",
    val VillageId: String? = "",
    val VillageName: String? = ""
): Serializable