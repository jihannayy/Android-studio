package com.jihannayla.laravelapi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jihannayla.laravelapi.databinding.ListSiswaAdapterBinding
import com.jihannayla.laravelapi.model.DataSiswa

class SiswaAdapter (
    private val listSiswa: List<DataSiswa?>?,
): RecyclerView.Adapter<SiswaAdapter.ViewHolder>() {
    inner class ViewHolder(val ListSiswaAdapterBinding: ListSiswaAdapterBinding):
    RecyclerView.ViewHolder(ListSiswaAdapterBinding.root) {
        fun onBindItem(dataSiswa: DataSiswa?) {
            ListSiswaAdapterBinding.namasiswa.text = dataSiswa?.namasiswa
            ListSiswaAdapterBinding.nisn.text = dataSiswa?.nisn
            ListSiswaAdapterBinding.alamat.text = dataSiswa?.alamat
            ListSiswaAdapterBinding.agama.text = dataSiswa?.agama
            ListSiswaAdapterBinding.usia.text = dataSiswa?.usia
            ListSiswaAdapterBinding.jeniskelamin.text = dataSiswa?.gender

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SiswaAdapter.ViewHolder {
        val binding =
            ListSiswaAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return  ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: SiswaAdapter.ViewHolder, position: Int) {
        holder.onBindItem(listSiswa?.get(position))

    }

    override fun getItemCount(): Int {
        return  listSiswa?.size?:0
    }

}