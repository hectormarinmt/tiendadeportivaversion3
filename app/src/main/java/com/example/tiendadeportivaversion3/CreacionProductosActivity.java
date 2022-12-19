package com.example.tiendadeportivaversion3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tiendadeportivaversion3.DB.DBFirebase;
import com.example.tiendadeportivaversion3.Entidades.Producto;

public class CreacionProductosActivity extends AppCompatActivity {
    private Button btnForm;
    private EditText editName, editDescription, editPrice;
    private DBFirebase dbFirebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creacion_productos);

        btnForm = (Button) findViewById(R.id.btnForm);
        editName = (EditText) findViewById(R.id.editName);
        editDescription = (EditText) findViewById(R.id.editDescription);
        editPrice = (EditText) findViewById(R.id.editPrice);

        dbFirebase = new DBFirebase();

        btnForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(editName.getText().toString().matches("")
                || editDescription.getText().toString().matches("")
                || editPrice.getText().toString().matches("")){
                    Toast.makeText(CreacionProductosActivity.this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
                }else{
                    Producto producto = new Producto(
                            editName.getText().toString(),
                            editDescription.getText().toString(),
                            editPrice.getText().toString(),
                            String.valueOf(R.drawable.balon)
                    );

                    dbFirebase.insertData(producto);
                    Intent intent = new Intent(getApplicationContext(), CatalogoActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}