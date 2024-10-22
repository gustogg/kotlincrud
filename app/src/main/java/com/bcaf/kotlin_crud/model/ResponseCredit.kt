package com.bcaf.kotlin_crud.model

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class ResponseCredit(

	@field:SerializedName("total")
	val total: Int? = null,

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Boolean? = null
) : Parcelable

@Parcelize
data class Data(

	@field:SerializedName("collection_data")
	val pengajuanCredit: List<PengajuanCreditItem?>? = null
) : Parcelable

@Parcelize
data class PengajuanCreditItem(

//	@field:SerializedName("foto_ktp")
//	val fotoKtp: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("alamat")
	val alamat: String? = null,

	@field:SerializedName("ospo")
	val ospo: String? = null,

	@field:SerializedName("username")
	val username: String? = null
) : Parcelable
