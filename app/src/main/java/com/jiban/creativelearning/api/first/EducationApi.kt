package com.jiban.creativelearning.api.first


import com.jiban.creativelearning.model.first.education.Education
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface EducationApi {

    @GET("{ServiceKey}/json/ListPublicReservationEducation/{pageNo}/{numOfRows}")
    suspend fun getEducationData(
        @Path("ServiceKey") ServiceKey: String = "76466d68766a6a733830667a784c49",
        @Path("pageNo") pageNo: Int,
        @Path("numOfRows") numOfRows: Int = 10,
    ): Education


    companion object {

        private const val BASE_URL = "http://openAPI.seoul.go.kr:8088/"

        operator fun invoke(): EducationApi = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(OkHttpClient.Builder().also { client ->
                val logging = HttpLoggingInterceptor()
                logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                client.addInterceptor(logging)
            }.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(EducationApi::class.java)
    }
}
