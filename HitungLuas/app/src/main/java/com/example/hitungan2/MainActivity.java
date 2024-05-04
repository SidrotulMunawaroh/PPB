package com.example.hitungan2;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private Spinner shapeSpinner;
    private EditText dimension1EditText;
    private EditText dimension2EditText;
    private Button calculateButton;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        shapeSpinner = findViewById(R.id.shapeSpinner);
        dimension1EditText = findViewById(R.id.dimension1EditText);
        dimension2EditText = findViewById(R.id.dimension2EditText);
        calculateButton = findViewById(R.id.calculateButton);
        resultTextView = findViewById(R.id.resultTextView);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.shapes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        shapeSpinner.setAdapter(adapter);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateArea();
            }
        });
    }

    private void calculateArea() {
        String shape = shapeSpinner.getSelectedItem().toString();
        double dimension1 = parseDouble(dimension1EditText.getText().toString());
        double dimension2 = parseDouble(dimension2EditText.getText().toString());

        double area = 0.0;

        if (shape.equals("Persegi Panjang")) {
            area = dimension1 * dimension2;
        } else if (shape.equals("Lingkaran")) {
            area = Math.PI * dimension1 * dimension1;
        } else if (shape.equals("Segitiga")) {
            area = 0.5 * dimension1 * dimension2;
        }

        DecimalFormat df = new DecimalFormat("#.##");
        resultTextView.setText("Luas " + shape + ": " + df.format(area));
    }

    private double parseDouble(String str) {
        try {
            return Double.parseDouble(str);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }
}
