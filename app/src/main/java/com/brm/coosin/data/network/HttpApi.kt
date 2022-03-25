package com.brm.coosin.data.network

import com.brm.coosin.data.model.Lead
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * Created by Rakhimjonov Shokhsulton on 23,март,2022
 * at Mayasoft LLC,
 * Tashkent, UZB.
 */
interface HttpApi {

    //registration Это END-POINT
    //https://cuisine-server.herokuapp.com это BASE URL
    @POST("/registration")
    fun getToken(@Body lead: Lead): Observable<String>


    @GET("/registration")
    fun getToken(
        @Query("username") username: String,
        @Query("password") password: String): Observable<String>
}