package com.nayla.konversisuhu;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
     Spinner spinner;
     EditText etNilai;
     TextView tvHasil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });

        load();
//        isiSpinner();
    }

    public void load(){
        spinner = findViewById(R.id.spinner);
        etNilai = findViewById(R.id.etNilai);
        tvHasil = findViewById(R.id.tvHasil);
    }


    /*
    public void isiSpinner() {
        String[] isi = {"Celcius To Reamur", "Celcius to Fahrenheit", "Celcius To Kelvin"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, isi);
        spinner.setAdapter(adapter);
    }


     */
    public void btnKonversi(View view) {
        String pilihan = spinner.getSelectedItem().toString();

        if(etNilai.getText().toString().equals("")){
            Toast.makeText(this, "Nilai Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
        }else{

            if(pilihan.equals("Celcius To Reamur")){

                cToR();
            }

            if(pilihan.equals("Celcius To Fahrenheit")){

                cToR();
            }





        }


        cToR();
    }

    public void cToR (){
            double suhu = Double.parseDouble(etNilai.getText().toString());
            double hasil = (4.0/5.0) * suhu;

            tvHasil.setText(hasil+"");


    }

    public void cToF(){
        Toast.makeText(this, "Belum Dibuat", Toast.LENGTH_SHORT).show();
    }

}
