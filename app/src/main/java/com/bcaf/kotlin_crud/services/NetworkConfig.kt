package com.bcaf.kotlin_crud.services

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkConfig {


    fun getRetrofit(): Retrofit {

        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor(HeaderInterceptor())
            .build()

        return Retrofit.Builder()
            .baseUrl("https://26a1-114-79-3-57.ngrok-free.app/cicool/api/")

            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
//    fun getServiceReimburstment() = getRetrofit().create(ReimburstmentServices::class.java)
    fun getServicePengajuanCredit() = getRetrofit().create(PengajuanCreditServices::class.java)

}