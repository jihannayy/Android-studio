package com.jihannayla.laravelapi.config

import com.jihannayla.laravelapi.model.ResponseListSiswa
import com.jihannayla.laravelapi.model.SubmitSiswa
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiServices {

    @GET("siswa")
    fun getSiswa(): retrofit2.Call<ResponseListSiswa>

    @GET("carisiswa")
    fun cariSiswa(@Query("cari") terms:String?): retrofit2.Call<ResponseListSiswa>

    @FormUrlEncoded
    @POST("siswa")
    fun postSiswa(
        @Field("namasiswa") namasiswa: String,
        @Field("nisn") nisn: String,
        @Field("alamat") alamat: String,
        @Field("gender") gender: String,
        @Field("agama") agama: String,
        @Field("usia") usia: String
    ): Call<SubmitSiswa>
}