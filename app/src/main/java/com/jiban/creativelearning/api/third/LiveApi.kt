package com.jiban.creativelearning.api.third

import com.jiban.creativelearning.model.third.live.Live
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface LiveApi {

    @GET("{ServiceKey}/json/walkSaengtaeInfo/{startIndex}/{endIndex}")
    suspend fun getLiveData(
        @Path("ServiceKey") ServiceKey: String = "56655455636a6a73333449424d4853",
        @Path("startIndex") startIndex: Int,
        @Path("endIndex") endIndex: Int
    ): Live

    companion object {

        private const val BASE_URL = "http://openAPI.seoul.go.kr:8088/"

        operator fun invoke(): LiveApi = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(OkHttpClient.Builder().also { client ->
                val logging = HttpLoggingInterceptor()
                logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                client.addInterceptor(logging)
            }.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(LiveApi::class.java)
    }
}

