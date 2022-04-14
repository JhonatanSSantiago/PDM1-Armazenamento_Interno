package com.jhonssantiago.arquivosandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {
    private EditText editTextTexto;
    private Button Salvar,Excluir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextTexto=findViewById(R.id.ETexto);
        Salvar=findViewById(R.id.BtS);
        Excluir=findViewById(R.id.BtE);

        Intent i = getIntent();
        editTextTexto.setText(i.getStringExtra("texto"));
    }

    public void clicar(View v){
        FileOutputStream fos = null;

        if (v.getId() == R.id.BtS){
            String conteudo = editTextTexto.getText().toString(); //obter o dado
            try {
                fos = openFileOutput("MyArq.txt", Context.MODE_PRIVATE); //se arq existir, sera aberto, se nao, sera criado. exclusivo do app
                fos.write(conteudo.getBytes()); //escreve no arq
                fos.close(); // sempre fechar arq
                Toast.makeText(getApplicationContext(), "Salvo", Toast.LENGTH_SHORT).show();
                editTextTexto.setText("");
                Intent i = new Intent(getApplicationContext(), SegundaActivity.class); //abrir 2 tela
                startActivity(i);
            } catch (FileNotFoundException e){
                e.printStackTrace();
                Log.e("erro1", "Arquivo não encrotado");
            } catch (IOException e) {
                e.printStackTrace();
                Log.e("erro2", "não foi possivel escrever");
            }
        }

        if (v.getId() == R.id.BtE){
            getApplicationContext().deleteFile("MyArq.txt"); //deletar arq
            editTextTexto.setText("");
            Toast.makeText(getApplicationContext(), "arquivo excluído", Toast.LENGTH_SHORT).show();
        }
    }
}