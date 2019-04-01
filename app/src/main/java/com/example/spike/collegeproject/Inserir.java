package com.example.spike.collegeproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Inserir extends AppCompatActivity {

    private BDSQLiteHelper bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        bd = new BDSQLiteHelper(this);


        Button btnConfirmar = (Button) findViewById(R.id.btnConfirm);
        Button btnCancelar = (Button) findViewById(R.id.btnCancel);
        final EditText edtNome = (EditText) findViewById(R.id.edtName);
        final EditText edtTipo = (EditText) findViewById(R.id.edtType);
        final EditText edtDescricao = (EditText) findViewById(R.id.edtDescription);

        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fornecedor fornecedor = new Fornecedor();
                fornecedor.setNome(edtNome.getText().toString());
                fornecedor.setTipo(edtTipo.getText().toString());
                fornecedor.setDescricao(edtDescricao.getText().toString());
                bd.addFornecedor(fornecedor);
                Toast.makeText(getBaseContext(), "Fornecedor inserido com sucesso",
                        Toast.LENGTH_LONG).show();
                Intent it = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(it);
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(it);
            }
        });
    }
}
