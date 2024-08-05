package com.example.sqlitedatabase;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Database db;
    EditText etBarang, etStok, etHarga;
    TextView tvPilihan;

    List<Barang> databarang = new ArrayList<Barang>();
    BarangAdapter adapter;
    RecyclerView rcvBarang;

    String idbarang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        load();
        selectData();
    }

    public void load() {
        db = new Database(this);
        db.buatTabel();

        etBarang = findViewById(R.id.etBarang);
        etStok = findViewById(R.id.etStok);
        etHarga = findViewById(R.id.etHarga);
        tvPilihan = findViewById(R.id.tvPilihan);
        rcvBarang = findViewById(R.id.rcvBarang);

        rcvBarang.setLayoutManager(new LinearLayoutManager(this));
        rcvBarang.setHasFixedSize(true);

    }

    public void simpan(View view) {
        String barang = etBarang.getText().toString();
        String stok = etStok.getText().toString();
        String harga = etHarga.getText().toString();
        String pilihan = tvPilihan.getText().toString();

        if (barang.isEmpty() || stok.isEmpty() || harga.isEmpty()) {
            pesan("KOSONG YA");
        } else {
            if (pilihan.equals("insert")) {
                String sql = "INSERT INTO tblbarang (barang,stok,harga) VALUES ('" + barang + "'," + stok + "," + harga + ")";
                pesan(sql);
                if (db.runSQL(sql)) {
                    pesan("UDH DISIMPAN");
                } else {
                    pesan("GABISA DISIMPAN");
                }


            } else {
               String sql = " UPDATE tblbarang\n" +
                       "SET barang =\'"+barang+"', stok = "+stok+", harga ="+harga+"\n" +
                       "WHERE idbarang = "+idbarang+";";
              if(db.runSQL(sql)){
                  pesan("UDAH DIUBAH YA");
                  selectData();
              }else{
                  pesan("GABISA DIUBAH");
              }
            }
        }
        etBarang.setText("");
        etStok.setText("");
        etHarga.setText("");
        tvPilihan.setText("insert");
    }

    public void pesan(String isi) {
        Toast.makeText(this, isi, Toast.LENGTH_SHORT).show();
    }

    public void selectData() {
        String sql = "SELECT * FROM tblbarang ORDER BY barang ASC";
        Cursor cursor = db.select(sql);
        databarang.clear();
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                @SuppressLint("Range") String idbarang = cursor.getString(cursor.getColumnIndex("idbarang"));
                @SuppressLint("Range") String barang = cursor.getString(cursor.getColumnIndex("barang"));
                @SuppressLint("Range") String stok = cursor.getString(cursor.getColumnIndex("stok"));
                @SuppressLint("Range") String harga = cursor.getString(cursor.getColumnIndex("harga"));
                databarang.add(new Barang(idbarang, barang, stok, harga));
            }
            adapter = new BarangAdapter(this, databarang);
            rcvBarang.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        } else {
            pesan("GAADA");
        }
    }

    public void deleteData(String id) {
        String idbarang = id;


        AlertDialog.Builder al = new AlertDialog.Builder(this);
        al.setTitle("WOY!!!");
        al.setMessage("YAKIN NIH DIAHPUS??");
        al.setPositiveButton("IYA SAYANG", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String sql = "DELETE FROM tblbarang WHERE idbarang = " + idbarang + ";";
                if (db.runSQL(sql)) {
                    pesan("UDH DIHAPUS YA");
                    selectData();
                } else {
                    pesan("GABISA DIHAPUS NIH");
                }
            }
        });
        al.setNegativeButton("ENGGA DONG SAYANG", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                dialog.cancel();
            }
        });
        al.show();

    }

    @SuppressLint("Range")
    public void selectupdate(String id) {
        idbarang = id;
        String sql = "SELECT * FROM tblbarang WHERE idbarang =" + id + ";";
        Cursor cursor = db.select(sql);
        cursor.moveToNext();
       etBarang.setText(cursor.getString(cursor.getColumnIndex("barang")));
       etStok.setText(cursor.getString(cursor.getColumnIndex("stok")));
       etHarga.setText(cursor.getString(cursor.getColumnIndex("harga")));
        tvPilihan.setText("update");
    }
}