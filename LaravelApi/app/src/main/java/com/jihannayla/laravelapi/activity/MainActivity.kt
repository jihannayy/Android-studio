package com.jihannayla.laravelapi.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.jihannayla.laravelapi.R
import com.jihannayla.laravelapi.activity.tambahData.TambahSiswaActivity
import com.jihannayla.laravelapi.adapter.SiswaAdapter
import com.jihannayla.laravelapi.config.NetworkConfig
import com.jihannayla.laravelapi.databinding.ActivityMainBinding
import com.jihannayla.laravelapi.model.ResponseListSiswa
import retrofit2.Call
import retrofit2.Response
import com.jihannayla.laravelapi.model.DataSiswa
import retrofit2.Callback

class MainActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {
    private  lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        val btnTambah = binding.btnTambah
        btnTambah.setOnClickListener{
            val tambah = Intent(this, TambahSiswaActivity::class.java)
            startActivity(tambah)
        }

        val swipeRefresh = binding.swipeRefreshLayout
        swipeRefresh.setOnRefreshListener(this)

        val appbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.app_bar_search)
        setSupportActionBar(appbar)
        getSiswa()



    }

    private fun getSiswa() {
        NetworkConfig().getServices()
            .getSiswa()
            .enqueue(object: Callback<ResponseListSiswa> {
                override fun onResponse(
                    call: Call<ResponseListSiswa>,
                    response: Response<ResponseListSiswa>
                ) {
                    this@MainActivity.binding.progressIndicator.visibility = View.GONE
                    if (response.isSuccessful) {
                        val receiveDatas = response.body()?.data
                        setToAdapter(receiveDatas)
                    }
                    binding.swipeRefreshLayout.isRefreshing = false
                }

                override fun onFailure(call: Call<ResponseListSiswa>, t: Throwable) {
                    this@MainActivity.binding.progressIndicator.visibility = View.GONE
                    Log.d("Retrofit onFailure: ", "onFailure: ${t.stackTrace}")
                    binding.swipeRefreshLayout.isRefreshing = false
                }
            })
    }

    private fun cariSiswa(query: String?) {
        this.binding.progressIndicator.visibility = View.GONE
        NetworkConfig().getServices()
            .cariSiswa(query)
            .enqueue(object : Callback<ResponseListSiswa> {
                override fun onResponse(
                    call: Call<ResponseListSiswa>,
                    response: Response<ResponseListSiswa>
                ) {
                    this@MainActivity.binding.progressIndicator.visibility = View.GONE
                    if (response.isSuccessful) {
                        val receiveDatas = response.body()?.data
                        setToAdapter(receiveDatas)
                    }
                }

                override fun onFailure(call: Call<ResponseListSiswa>, t: Throwable) {
                    this@MainActivity.binding.progressIndicator.visibility = View.GONE
                    Log.d("Retrofit onFailure: ", "onFailure: ${t.stackTrace}")

                }

            })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        val cariItem = menu?.findItem(R.id.app_bar_search)
        val cariView : SearchView= cariItem?.actionView as SearchView
        cariView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                cariSiswa(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

        })
        return  true
    }

    private fun setToAdapter(receiveDatas: List<DataSiswa?>?) {
        val adapter = SiswaAdapter(receiveDatas)
        val lm = LinearLayoutManager(this)
        this.binding.rvSiswa.layoutManager = lm
        this.binding.rvSiswa.itemAnimator = DefaultItemAnimator()
        this.binding.rvSiswa.adapter = adapter
    }

    override fun onRefresh() {
        getSiswa()
    }
}