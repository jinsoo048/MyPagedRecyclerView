package com.jiban.creativelearning.api.second

import com.jiban.creativelearning.model.second.travel.Travel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface TravelApi {

    @GET("openapi/service/rest/PhotoGalleryService/galleryList")
    suspend fun getTravelData(
        @Query("ServiceKey") ServiceKey: String = "oxkeiOH8uK8oW7g0PbEl5%2F1XxEPIdlvQtZnFkmMONh8qI3VCVBLjtyn0Q9LCS5ICwiC0oE9SmI7pEaH%2F2IzfDg%3D%3D",
        @Query("pageNo") pageNo: Int,
        @Query("numOfRows") numOfRows: Int = 10,
        @Query("MobileOS") MobileOS: String,
        @Query("MobileApp") MobileApp: String,
        @Query("_type") _type: String = "json"
    ): Travel

    companion object {

        private const val BASE_URL = "http://api.visitkorea.or.kr/"

        operator fun invoke(): TravelApi = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(OkHttpClient.Builder().also { client ->
                val logging = HttpLoggingInterceptor()
                logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                client.addInterceptor(logging)
            }.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TravelApi::class.java)
    }
}

