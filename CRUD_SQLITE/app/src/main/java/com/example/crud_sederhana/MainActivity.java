package com.example.crud_sederhana;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextInputEditText editTextId, editTextName;
    private TextInputLayout textInputLayoutId, textInputLayoutName;
    private Button buttonCreate, buttonUpdate, buttonDelete;
    private ListView listView;
    private DatabaseHelper dbHelper;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextId = findViewById(R.id.editTextId);
        editTextName = findViewById(R.id.editTextName);
        textInputLayoutId = findViewById(R.id.textInputLayoutId);
        textInputLayoutName = findViewById(R.id.textInputLayoutName);
        buttonCreate = findViewById(R.id.buttonCreate);
        buttonUpdate = findViewById(R.id.buttonUpdate);
        buttonDelete = findViewById(R.id.buttonDelete);
        listView = findViewById(R.id.listView);
        dbHelper = new DatabaseHelper(this);

        buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString();

                if (name.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter name", Toast.LENGTH_SHORT).show();
                } else {
                    long insertedId = dbHelper.insertData(name);
                    if (insertedId != -1) {
                        Toast.makeText(MainActivity.this, "Data inserted with ID: " + insertedId, Toast.LENGTH_SHORT).show();
                        refreshData();
                        editTextName.setText("");
                        textInputLayoutName.setError(null);
                    } else {
                        Toast.makeText(MainActivity.this, "Failed to insert data", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idString = editTextId.getText().toString();
                String newName = editTextName.getText().toString();

                if (idString.isEmpty() || newName.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter ID and new name", Toast.LENGTH_SHORT).show();
                } else {
                    int id = Integer.parseInt(idString);
                    dbHelper.updateData(id, newName);
                    Toast.makeText(MainActivity.this, "Data updated", Toast.LENGTH_SHORT).show();
                    refreshData();
                    editTextId.setText("");
                    editTextName.setText("");
                    textInputLayoutId.setError(null);
                    textInputLayoutName.setError(null);
                }
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idString = editTextId.getText().toString();
                if (idString.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter ID to delete", Toast.LENGTH_SHORT).show();
                } else {
                    int id = Integer.parseInt(idString);
                    dbHelper.deleteData(id);
                    Toast.makeText(MainActivity.this, "Data deleted", Toast.LENGTH_SHORT).show();
                    refreshData();
                    editTextId.setText("");
                    editTextName.setText("");
                    textInputLayoutId.setError(null);
                    textInputLayoutName.setError(null);
                }
            }
        });

        refreshData();
    }

    private void refreshData() {
        List<String> dataList = dbHelper.getData();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataList);
        listView.setAdapter(adapter);
    }
}


