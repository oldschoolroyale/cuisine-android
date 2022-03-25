package com.brm.coosin.data.network

import com.brm.coosin.data.model.Lead
import com.brm.coosin.util.StringConverterFactory
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Rakhimjonov Shokhsulton on 23,март,2022
 * at Mayasoft LLC,
 * Tashkent, UZB.
 */
class ApiServiceImpl: ApiService {

    companion object{
        private const val BASE_URL = "https://cuisine-server.herokuapp.com"
    }

    private var retrofit: Retrofit? = null
    private var httpApi: HttpApi? = null

    private fun getHttpApi(): HttpApi{
        if (retrofit == null) {
            val okHttpClientBuilder = OkHttpClient.Builder()
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            okHttpClientBuilder.addInterceptor(logging)
            val client : OkHttpClient = okHttpClientBuilder.connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .cache(null)
                .build()
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(StringConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build()
        }
        if (httpApi == null){
            httpApi = retrofit!!.create(HttpApi::class.java)
        }
        return httpApi!!
    }
    override fun register(lead: Lead): Observable<String> {
        return getHttpApi().getToken(lead)
            .subscribeOn(Schedulers.newThread()) // Видишь, переводится как подпишись и создай новый поток
            .observeOn(AndroidSchedulers.mainThread()) // Ответ верни на основной поток,
                                                        //который занимается отрисовкой элементов
        //Он создает новый поток и делает интернет запрос, чтобы не блокировать отрисовку элементов
        //Андроид тебе не позволит на основном потоке делать интернет запрос
    }

    override fun signIn(username: String, password: String): Observable<String> {
        return getHttpApi().getToken(username, password)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
    }


}