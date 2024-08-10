package com.nayla.sqlitedatabase;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Database db;
    EditText etBarang, etStok, etHarga;
    TextView tvPilihan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        load();



    }

    public void load(){
        db = new Database(this);
        db.buatTabel();

        etBarang = findViewById(R.id.etBarang);
        etStok = findViewById(R.id.etStok);
        etHarga = findViewById(R.id.etHarga);
        tvPilihan = findViewById(R.id.tvPilihan);

    }

    public void simpan(View v){
         String barang = etBarang.getText().toString();
         String stok = etStok.getText().toString();
         String harga = etHarga.getText().toString();
         String pilihan = tvPilihan.getText().toString();

        if (barang.isEmpty() || stok.isEmpty() || harga.isEmpty()){
            pesan("Data Kosong");
        }else{
           if (pilihan.equals("insert")){
               String sql = "INSERT INTO tblbarang (barang, stok, harga) VALUES ('"+barang+"',"+stok+", "+harga+")";
               if(db.runSQL(sql)){
                   pesan("insert berhasil");
               }else {
                   pesan("Insert gagal");
               }

           }else {
               pesan("update");
           }
        }

        etBarang.setText("");
        etStok.setText("");
        etHarga.setText("");
        tvPilihan.setText("insert");

    }




    public void pesan (String isi){
        Toast.makeText(this, isi, Toast.LENGTH_SHORT).show();
    }
}