package com.jiban.creativelearning.api.second

import com.jiban.creativelearning.model.second.camp.Camp
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface CampApi {

    @GET("basedList")
    suspend fun getCampData(
        @Query("ServiceKey") ServiceKey: String = "oxkeiOH8uK8oW7g0PbEl5%2F1XxEPIdlvQtZnFkmMONh8qI3VCVBLjtyn0Q9LCS5ICwiC0oE9SmI7pEaH%2F2IzfDg%3D%3D",
        @Query("pageNo") pageNo: Int,
        @Query("numOfRows") numOfRows: Int = 10,
        @Query("MobileOS") MobileOS: String,
        @Query("MobileApp") MobileApp: String,
        @Query("_type") _type: String = "json"
    ): Camp

    companion object {

        private const val BASE_URL = "http://api.visitkorea.or.kr/openapi/service/rest/GoCamping/"

        operator fun invoke(): CampApi = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(OkHttpClient.Builder().also { client ->
                val logging = HttpLoggingInterceptor()
                logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                client.addInterceptor(logging)
            }.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CampApi::class.java)
    }
}

