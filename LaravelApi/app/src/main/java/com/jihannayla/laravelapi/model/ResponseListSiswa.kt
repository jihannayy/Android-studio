package com.jihannayla.laravelapi.model

import com.google.gson.annotations.SerializedName

data class ResponseListSiswa(
	@field:SerializedName("data")
	val data: List<DataSiswa?>? = null,

	@field:SerializedName("success")
	val success: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
)
data class DataSiswa(

	@field:SerializedName("usia")
	val usia: String? = null,

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("idsiswa")
	val idsiswa: Int? = null,

	@field:SerializedName("gender")
	val gender: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("nisn")
	val nisn: String? = null,

	@field:SerializedName("agama")
	val agama: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("namasiswa")
	val namasiswa: String? = null,

	@field:SerializedName("ttl")
	val ttl: String? = null,

	@field:SerializedName("alamat")
	val alamat: String? = null
)

