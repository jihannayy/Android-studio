package com.nayla.recyclercardview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SiswaAdapter extends RecyclerView.Adapter<SiswaAdapter.Viewolder> {

    private Context context;
    private List<Siswa>siswaList;

    public SiswaAdapter(Context context, List<Siswa> siswaList) {
        this.context = context;
        this.siswaList = siswaList;
    }

    @NonNull
    @Override
    public Viewolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_siswa, viewGroup, false);
        return new Viewolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewolder viewHolder, @SuppressLint("RecyclerView") int i) {
        Siswa siswa = siswaList.get(i);
        viewHolder.tvNama.setText(siswa.getNama());
        viewHolder.tvAlamat.setText(siswa.getAlamat());


        /*
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Nama :"+siswa.getNama()+"Alamat : "+siswa.getAlamat(), Toast.LENGTH_SHORT).show();
            }
        });


         */

        viewHolder.tvMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(context,viewHolder.tvMenu);
                popupMenu.inflate(R.menu.menu_option);

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        int id = item.getItemId();

                        if (id == R.id.menu_simpan) {

                            Toast.makeText(context, "Simpan Data"+siswa.getNama(), Toast.LENGTH_SHORT).show();

                        }else if (id == R.id.menu_hapus){
                            siswaList.remove(i);
                            notifyDataSetChanged();
                            Toast.makeText(context, "Sudah Dihapus"+siswa.getNama(), Toast.LENGTH_SHORT).show();
                        }
                         return false;
                    }
                });

                popupMenu.show();





            }

        });

    }



    @Override
    public int getItemCount() {
        return siswaList.size();
    }

    public class Viewolder extends RecyclerView.ViewHolder{

        TextView tvNama, tvAlamat, tvMenu;


        public Viewolder(@NonNull View itemView) {
            super(itemView);

            tvNama = itemView.findViewById(R.id.tvNama);
            tvAlamat = itemView.findViewById(R.id.tvAlamat);
            tvMenu = itemView.findViewById(R.id.tvMenu);
        }
    }
}
