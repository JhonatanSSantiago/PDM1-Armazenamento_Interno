package com.jhonssantiago.arquivosandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SegundaActivity extends AppCompatActivity {
    private TextView textViewTexto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda);

        textViewTexto=findViewById(R.id.TV);
        String texto = getInfo();
        textViewTexto.setText(texto);
    }

    private String getInfo(){
        StringBuffer sb = null;
        try {
            FileInputStream fis = openFileInput("MyArq.txt"); //solicicta a abertura do arq no modo leitura, precis existir
            sb = new StringBuffer();
            int retorno = 0;
            char texto = 0;
            while((retorno = fis.read()) !=-1){ // read() delvove o caract, presente no arquivo, valor -1 indica fim do arqu
                texto = (char)retorno;
                sb.append(texto); // adiciona o carac no StringBuff
            }
            fis.close(); //encerra o arquivo
        }catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        return sb.toString();
    }

    public void abrirTela(View v){
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        i.putExtra("texto", textViewTexto.getText().toString());
        startActivity(i);
    }
}