package com.moddakir.moddakir.network.remote


import com.moddakir.moddakir.network.Session
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class NoneAuthInterceptor @Inject constructor() : Interceptor {

    private val CONTENT_TYPE = "Content-Type"
    private val APP_VERSION = "AppVersion"
    private val Language = "Accept-Language"
    private val CONTENT_TYPE_VALUE = "application/json"

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val authorisedRequestBuilder = originalRequest.newBuilder()
            .header(CONTENT_TYPE, CONTENT_TYPE_VALUE)
            .addHeader(APP_VERSION, "")
            .addHeader(Language, Session.xLang)
           // .method(originalRequest.method, originalRequest.body)

        val response = chain.proceed(authorisedRequestBuilder.build())
        return response
    }
}
