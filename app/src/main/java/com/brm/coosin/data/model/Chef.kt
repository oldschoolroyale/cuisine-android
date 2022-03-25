package com.brm.coosin.data.model

/**
 * Created by Rakhimjonov Shokhsulton on 25,март,2022
 * at Mayasoft LLC,
 * Tashkent, UZB.
 */
data class Chef(
    val chiefName: String,
    val chiefNationality: String? = null,
    val chiefRecommend: String? = null,
    val chiefImage: Int,
    val chiefBackground: String? = null,
    val chiefAdvertisement: String? = null
)