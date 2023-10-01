package com.example.syz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView logo;
    private Button cadastraCliente;
    private Button cadastraProduto;
    private Button cadastraPedido;
    private Button buscaPedido;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logo = findViewById(R.id.ivLogo);
        cadastraCliente = findViewById(R.id.btCadasCliente);
        cadastraProduto = findViewById(R.id.btCadasProduto);
        cadastraPedido = findViewById(R.id.btCadasPedido);
        buscaPedido = findViewById(R.id.btBuscaPedido);

        logo.setImageResource(R.drawable.syz_no_background);

        cadastraCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirActivity(Cadastra_Cliente_Activity.class);
            }
        });

        cadastraProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirActivity(CadastraProdutoActivity.class);
            }
        });

        cadastraPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirActivity(CadastraPedidoActivity.class);
            }
        });

        buscaPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirActivity(BuscaPedidoActivity.class);
            }
        });

    }

    private void abrirActivity(Class<?> activity){

        Intent intent = new Intent(MainActivity.this, activity);
        startActivity(intent);
    }
}