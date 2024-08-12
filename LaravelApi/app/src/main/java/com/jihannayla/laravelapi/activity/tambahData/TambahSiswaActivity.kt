package com.jihannayla.laravelapi.activity.tambahData

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.jihannayla.laravelapi.R
import com.jihannayla.laravelapi.config.NetworkConfig
import com.jihannayla.laravelapi.databinding.ActivityTambahSiswaBinding
import com.jihannayla.laravelapi.model.SubmitSiswa
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TambahSiswaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTambahSiswaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityTambahSiswaBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val items = listOf("Islam", "Kristen","Katolik", "KongHuChu", "Hindu", "Budha")
        val adapter = ArrayAdapter(this, R.layout.list_agama, items);
        binding.dropdownAgama.setAdapter(adapter)

        binding.saveButton.setOnClickListener{
            saveData()
        }


    }

    private fun saveData() {
        val agama = binding.dropdownAgama.text.toString()
        val rbLaki = binding.rbLaki
        val rbPerempuan = binding.rbPerempuan

        val gender = if (rbLaki.isChecked) {
            rbLaki.text.toString()
        } else {
            rbPerempuan.text.toString()
        }

        val namaLengkap = binding.editTextName.text.toString()
        val nisn = binding.etNisn.text.toString()
        val alamat = binding.etAlamat.text.toString()
        val usia = binding.etUsia.text.toString()

        val retrofit = NetworkConfig().getServices()

        if (!(namaLengkap.isEmpty() && nisn.isEmpty() && alamat.isEmpty() && gender.isEmpty()&& agama.isEmpty()&& usia.isEmpty())) {
            retrofit.postSiswa(namaLengkap, nisn,alamat, gender, agama, usia)
                .enqueue(object : Callback<SubmitSiswa> {
                    override fun onResponse(
                        call: Call<SubmitSiswa>,
                        response: Response<SubmitSiswa>
                    ) {
                        if(response.isSuccessful) {
                            val hasil = response.body()
                            Toast.makeText(applicationContext, hasil!!.message, Toast.LENGTH_SHORT).show()
                            namaLengkap.isEmpty()
                            nisn.isEmpty()
                            alamat.isEmpty()
                            gender.isEmpty()
                            agama.isEmpty()
                            usia.isEmpty()
                        }
                    }

                    override fun onFailure(call: Call<SubmitSiswa>, t: Throwable) {
                        Toast.makeText(applicationContext, "Data dapat disimpan : ${t.message}" ,
                            Toast.LENGTH_SHORT).show()
                    }

                })
        } else {
            Toast.makeText(applicationContext, "Field tidak boleh kosong",
                Toast.LENGTH_SHORT).show()
        }

    }
}