package com.example.spike.collegeproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.function.Supplier;

public class Inicial extends AppCompatActivity {

    private BDSQLiteHelper bd;

    ListView lvListaFornecedores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);
        bd = new BDSQLiteHelper(this);
        lvListaFornecedores = (ListView) findViewById(R.id.lvSuppliersList);


        Button btnInserir = (Button)findViewById(R.id.btnRegister);

        btnInserir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(), Inserir.class);
                startActivity(it);
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();

        final ArrayList<Fornecedor> listaLivros = bd.getAllFornecedores();
        ArrayAdapter<Fornecedor> adapter = new ArrayAdapter<Fornecedor>(this,
                android.R.layout.simple_list_item_1, listaLivros);
        lvListaFornecedores.setAdapter(adapter);


        lvListaFornecedores.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(getApplicationContext(), Alterar.class);

                it.putExtra("id", listaLivros.get(position).getId());
                startActivity(it);
            }
        });
    }
}