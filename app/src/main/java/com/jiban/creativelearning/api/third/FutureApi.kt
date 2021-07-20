package com.jiban.creativelearning.api.third

import com.jiban.creativelearning.model.third.future.Future
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface FutureApi {

    @GET("{ServiceKey}/json/futureCourseInfo/{startIndex}/{endIndex}")
    suspend fun getFutureData(
        @Path("ServiceKey") ServiceKey: String = "4c6f5066626a6a7334386e7757434c",
        @Path("startIndex") startIndex: Int,
        @Path("endIndex") endIndex: Int
    ): Future

    companion object {

        private const val BASE_URL = "http://openAPI.seoul.go.kr:8088/"

        operator fun invoke(): FutureApi = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(OkHttpClient.Builder().also { client ->
                val logging = HttpLoggingInterceptor()
                logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                client.addInterceptor(logging)
            }.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FutureApi::class.java)
    }
}

