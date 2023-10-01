package com.example.syz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.syz.models.Cliente;

public class Cadastra_Cliente_Activity extends AppCompatActivity {

    private EditText nome;
    private EditText cpf;
    private Button cadastrar;
    private TextView mostraCliente;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastra_cliente);
        setTitle("Cadastro de Clientes");

        nome = findViewById(R.id.edNomeCliente);
        mostraCliente = findViewById(R.id.tvListaCliente);
        cpf = findViewById(R.id.edCpfCliente);
        cadastrar = findViewById(R.id.btCadastraCliente);

        atualizaCliente();

        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cadastrarCliente();
            }
        });



    }

    private void cadastrarCliente() {
        if(nome.getText().toString().isEmpty()){
            nome.setError("Este campo deve ser preenchido!");
            nome.requestFocus();
            return;
        }
        if(cpf.getText().toString().isEmpty()){
            cpf.setError("Este campo deve ser preenchido");
            cpf.requestFocus();
            return;
        }

        Cliente cliente = new Cliente();
        cliente.setNome(nome.getText().toString());
        cliente.setCpf(cpf.getText().toString());
        Controller.getInstance().salvarCliente(cliente);
        Toast.makeText(this, "Cliente cadastrado com sucesso!!", Toast.LENGTH_LONG).show();
        this.finish();
    }

    private void atualizaCliente(){
        String text = "";
        for (Cliente cliente: Controller.getInstance().retornaCliente()){
            text += "Nome: "+ cliente.getNome() + "\nCPF: "+ cliente.getCpf()+"\n"+
                "-------------------------------------\n";
        }
        mostraCliente.setText(text);
    }
}