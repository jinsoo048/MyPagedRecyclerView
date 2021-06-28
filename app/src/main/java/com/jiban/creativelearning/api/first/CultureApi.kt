package com.jiban.creativelearning.api.first

import com.jiban.creativelearning.model.first.culture.PublicReservationCulture
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface CultureApi {

    @GET("{ServiceKey}/json/ListPublicReservationCulture/{pageNo}/{numOfRows}")
    suspend fun getCultureData(
        @Path("ServiceKey") ServiceKey: String = "6c566673796a6a733839664d4a624d",
        @Path("pageNo") pageNo: Int,
        @Path("numOfRows") numOfRows: Int = 10,
    ): PublicReservationCulture


    companion object {

        private const val BASE_URL = "http://openAPI.seoul.go.kr:8088/"

        operator fun invoke(): CultureApi = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(OkHttpClient.Builder().also { client ->
                val logging = HttpLoggingInterceptor()
                logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                client.addInterceptor(logging)
            }.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CultureApi::class.java)
    }
}

