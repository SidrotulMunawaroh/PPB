package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText editTextAngka1, editTextAngka2;
    private TextView textViewHasil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextAngka1 = findViewById(R.id.editTextNumber1);
        editTextAngka2 = findViewById(R.id.editTextNumber2);
        textViewHasil = findViewById(R.id.textViewHasil);
    }

    public void tambah(View view) {
        double angka1 = Double.parseDouble(editTextAngka1.getText().toString());
        double angka2 = Double.parseDouble(editTextAngka2.getText().toString());
        double hasil = angka1 + angka2;
        tampilkanHasil(hasil);
    }

    public void kurang(View view) {
        double angka1 = Double.parseDouble(editTextAngka1.getText().toString());
        double angka2 = Double.parseDouble(editTextAngka2.getText().toString());
        double hasil = angka1 - angka2;
        tampilkanHasil(hasil);
    }

    public void kali(View view) {
        double angka1 = Double.parseDouble(editTextAngka1.getText().toString());
        double angka2 = Double.parseDouble(editTextAngka2.getText().toString());
        double hasil = angka1 * angka2;
        tampilkanHasil(hasil);
    }

    public void bagi(View view) {
        double angka1 = Double.parseDouble(editTextAngka1.getText().toString());
        double angka2 = Double.parseDouble(editTextAngka2.getText().toString());
        if (angka2 != 0) {
            double hasil = angka1 / angka2;
            tampilkanHasil(hasil);
        } else {
            textViewHasil.setText("Tidak bisa dibagi dengan nol");
        }
    }

    public void keluar(View view) {
        finish();
    }

    private void tampilkanHasil(double hasil) {
        if (hasil % 1 == 0) {
            // Jika hasil merupakan bilangan bulat
            textViewHasil.setText("Hasil: " + (int) hasil);
        } else {
            // Jika hasil memiliki desimal
            textViewHasil.setText("Hasil: " + hasil);
        }
    }
}
