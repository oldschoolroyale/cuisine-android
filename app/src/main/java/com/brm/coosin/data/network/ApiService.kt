package com.brm.coosin.data.network

import com.brm.coosin.data.model.Lead
import io.reactivex.Observable

/**
 * Created by Rakhimjonov Shokhsulton on 23,март,2022
 * at Mayasoft LLC,
 * Tashkent, UZB.
 */
interface ApiService {
    fun register(lead: Lead): Observable<String>

    fun signIn(username: String, password: String): Observable<String>
}