package com.bcaf.kotlin_crud.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bcaf.kotlin_crud.model.ResponseCredit
import com.bcaf.kotlin_crud.model.ResponseServices
import com.bcaf.kotlin_crud.services.NetworkConfig
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response

class PengajuanCreditViewModel(application: Application) : AndroidViewModel(application) {
    private val _post = MutableLiveData<ResponseServices>()
    private val application: Application

    val post: LiveData<ResponseServices>
        get() = _post

    private val _getPengajuanCredit = MutableLiveData<ResponseCredit>()

    val getPengajuanCredit: LiveData<ResponseCredit>
        get() = _getPengajuanCredit

    init {
        this.application = application
        getPengajuanCredit()
    }

    fun postDataCredit(username: RequestBody, alamat: RequestBody, ospo: RequestBody) {
        NetworkConfig().getServicePengajuanCredit().addPengajuanCredit(username, alamat, ospo)
            .enqueue(object : retrofit2.Callback<ResponseServices> {
                override fun onResponse(p0: Call<ResponseServices>, p1: Response<ResponseServices>) {
                    _post.postValue(p1.body())
                }

                override fun onFailure(p0: Call<ResponseServices>, p1: Throwable) {
                    Toast.makeText(application.applicationContext, "Gagal Mengupload Data Credit", Toast.LENGTH_SHORT).show()
                }
            })
    }

    fun getPengajuanCredit() {
        NetworkConfig().getServicePengajuanCredit().getAllPengajuanCredit()
            .enqueue(object : retrofit2.Callback<ResponseCredit> {
                override fun onResponse(p0: Call<ResponseCredit>, p1: Response<ResponseCredit>) {
                    _getPengajuanCredit.postValue(p1.body())
                }

                override fun onFailure(p0: Call<ResponseCredit>, p1: Throwable) {

                }
            })
    }

    fun getSharePreferencesLogin(key: String): String {
        return application.getSharedPreferences("LOGIN", 0).getString(key, "").toString()
    }
    fun deletePengajuanCredit(id: RequestBody) {
        NetworkConfig().getServicePengajuanCredit().deletePengajuanCredit(id)
            .enqueue(object : retrofit2.Callback<ResponseServices> {
                override fun onResponse(p0: Call<ResponseServices>, p1: Response<ResponseServices>) {
                    _post.postValue(p1.body())
                    // Call the new method to refresh data after deletion
                    if (p1.isSuccessful) {
                        fetchUpdatedData() // Refresh the data
                    }
                }

                override fun onFailure(p0: Call<ResponseServices>, p1: Throwable) {
                    Toast.makeText(application.applicationContext, "Gagal Delete Data Credit", Toast.LENGTH_SHORT).show()
                }
            })
    }

    // New method to refresh the data
    fun fetchUpdatedData() {
        getPengajuanCredit() // Call the existing method to fetch data again
    }


}
