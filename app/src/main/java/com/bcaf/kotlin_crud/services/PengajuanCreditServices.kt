package com.bcaf.kotlin_crud.services

import com.bcaf.kotlin_crud.model.ResponseCredit
import com.bcaf.kotlin_crud.model.ResponseServices
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface PengajuanCreditServices {

    @Multipart
    @POST("collection_data/add")
    fun addPengajuanCredit(
        @Part("username") username: RequestBody,
        @Part("alamat") alamat: RequestBody,
        @Part("ospo") ospo: RequestBody
    ): Call<ResponseServices>

    @GET("collection_data/all")
    fun getAllPengajuanCredit(): Call<ResponseCredit>

//    // Update function using PUT
//    @Multipart
//    @POST("collection_data/update")
//    fun updatePengajuanCredit(
//        @Path("id") id: Int,
//        @Part("username") username: RequestBody,
//        @Part("alamat") alamat: RequestBody,
//        @Part("ospo") ospo: RequestBody
//    ): Call<ResponseServices>

    // Delete function
    @Multipart
    @POST("collection_data/delete")
    fun deletePengajuanCredit(
        @Part("id") id: RequestBody,

    ): Call<ResponseServices>

}
