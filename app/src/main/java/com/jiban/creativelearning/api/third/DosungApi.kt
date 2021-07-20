package com.jiban.creativelearning.api.third

import com.jiban.creativelearning.model.third.dosung.Dosung
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface DosungApi {

    @GET("{ServiceKey}/json/walkDoseongInfo/{startIndex}/{endIndex}")
    suspend fun getDosungData(
        @Path("ServiceKey") ServiceKey: String = "41547274756a6a7335326d6f475450",
        @Path("startIndex") startIndex: Int,
        @Path("endIndex") endIndex: Int
    ): Dosung

    companion object {

        private const val BASE_URL = "http://openAPI.seoul.go.kr:8088/"

        operator fun invoke(): DosungApi = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(OkHttpClient.Builder().also { client ->
                val logging = HttpLoggingInterceptor()
                logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                client.addInterceptor(logging)
            }.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DosungApi::class.java)
    }
}

