package com.example.spike.collegeproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText Nome;
    private EditText Password;
    private TextView Info;
    private Button Login;
    private Button Cancelar;
    private int counter = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Nome = (EditText)findViewById(R.id.edtName);
        Password = (EditText)findViewById(R.id.edtPassword);
        Info = (TextView)findViewById(R.id.tvInfo);
        Login = (Button)findViewById(R.id.btnLogin);
        Cancelar = (Button)findViewById(R.id.btnCancel);


        Info.setText("Number of attempts remain - Número de tentativas restantes: 5");

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(Password.getText().toString());
            }
        });

        Cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });
    }

    private void validate(String userPassword){
        if((userPassword.equals("123456"))) {
            Intent intent = new Intent(MainActivity.this, Inicial.class);
            startActivity(intent);
        }else {
            counter--;

            Info.setText("Number of attempts remain - Número de tentativas restantes: " + String.valueOf(counter));

            if(counter == 0) {
                Login.setEnabled(false);
            }
        }

    }
}