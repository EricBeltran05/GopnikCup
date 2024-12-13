package com.codefastly.gopnikcupapp.commons.service

import android.util.Log
import com.codefastly.gopnikcupapp.commons.service.dataclass.Session
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

class AssettoServerUseCase {
    private val BASE_URL = "http://192.168.1.50:8081/api/"
    private val TAG = javaClass.simpleName

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(getUnsafeOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val api: AssettoCorsaDatasource by lazy {
        retrofit.create(AssettoCorsaDatasource::class.java)
    }

    fun execute(){
        api.getLiveData().enqueue(object : Callback<LiveDataResponse> {
            override fun onResponse(call: Call<LiveDataResponse>, response: Response<LiveDataResponse>) {
                if (response.isSuccessful) {
                    val data = response.body()
                    Log.d("API", "Session Status: ${data?.session?.status}")
                } else {
                    Log.e("API", "Error: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<LiveDataResponse>, t: Throwable) {
                Log.e("API", "Error: ${t.message}")
            }
        })
    }

    private fun getUnsafeOkHttpClient(): OkHttpClient {
        try {
            // Crea un administrador de confianza que acepte cualquier certificado
            val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
                override fun checkClientTrusted(
                    chain: Array<java.security.cert.X509Certificate>,
                    authType: String
                ) {
                }

                override fun checkServerTrusted(
                    chain: Array<java.security.cert.X509Certificate>,
                    authType: String
                ) {
                }

                override fun getAcceptedIssuers(): Array<java.security.cert.X509Certificate> =
                    arrayOf()
            })

            // Instala el administrador de confianza
            val sslContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCerts, java.security.SecureRandom())
            val sslSocketFactory = sslContext.socketFactory

            // Construye el cliente OkHttp personalizado
            return OkHttpClient.Builder()
                .sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
                .hostnameVerifier { _, _ -> true }
                .build()
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}