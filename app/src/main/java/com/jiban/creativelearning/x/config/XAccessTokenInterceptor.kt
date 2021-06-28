package com.jiban.creativelearning.x.config

import com.jiban.creativelearning.x.config.ApplicationClass.Companion.X_ACCESS_TOKEN
import com.jiban.creativelearning.x.config.ApplicationClass.Companion.sSharedPreferences
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class XAccessTokenInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder: Request.Builder = chain.request().newBuilder()
        var jwtToken: String? = sSharedPreferences.getString(X_ACCESS_TOKEN, null)

        //imsi로 static하게 사용
        jwtToken = "6c566673796a6a733839664d4a624d"

        if (jwtToken != null) {
            builder.addHeader("X-ACCESS-TOKEN", jwtToken)
        }
        return chain.proceed(builder.build())
    }
}