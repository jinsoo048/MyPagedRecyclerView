package com.jiban.creativelearning.api.first


import com.jiban.creativelearning.model.first.education.Education
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface EducationApi {

    @GET("{serviceKey}/json/ListPublicReservationEducation/{startIndex}/{endIndex}")
    suspend fun getEducationData(
        @Path("serviceKey") ServiceKey: String = "76466d68766a6a733830667a784c49",
        @Path("startIndex") startIndex: Int,
        @Path("endIndex") endIndex: Int
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
