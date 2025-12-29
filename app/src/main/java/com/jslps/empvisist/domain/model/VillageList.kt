package com.jslps.empvisist.domain.model

import java.io.Serializable

data class VillageList(
    val ClfCode: String,
    val PanchayatCode: String,
    val LgdVillage: String,
    val VillageCode: String,
    val VillageId: String,
    val VillageName: String
): Serializable