package com.example.syz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.syz.models.Produto;

public class CadastraProdutoActivity extends AppCompatActivity {

    private EditText edDescricao;
    private EditText edCodigo;
    private EditText edValorUn;
    private Button btSalvaProduto;
    private TextView mostraProduto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastra_produto);
        setTitle("Cadastro de Produto");

        edDescricao = findViewById(R.id.edDescricao);
        edCodigo = findViewById(R.id.edCodigo);
        edValorUn = findViewById(R.id.edValorUn);
        btSalvaProduto = findViewById(R.id.btSalvaProduto);
        mostraProduto = findViewById(R.id.tvMostraProduto);

        atualizaProduto();

        btSalvaProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvarProduto();
            }
        });
    }

    private void salvarProduto() {
        if(edDescricao.getText().toString().isEmpty()){
            edDescricao.setError("O campo deve ser preenchido");
            edDescricao.requestFocus();
            return;
        }
        if(edCodigo.getText().toString().isEmpty()){
            edCodigo.setError("O campo deve ser preenchido");
            edCodigo.requestFocus();
            return;
        }
        if(edValorUn.getText().toString().isEmpty()){
            edValorUn.setError("O campo deve ser preenchido");
            edValorUn.requestFocus();
            return;
        }

        Produto produto = new Produto();
        produto.setCodigo(edCodigo.getText().toString());
        produto.setDescricao(edDescricao.getText().toString());
        produto.setValorUnit(Double.parseDouble(edValorUn.getText().toString()));

        Controller.getInstance().salvarProduto(produto);

        Toast.makeText(CadastraProdutoActivity.this,"Produto cadastrado com sucesso",Toast.LENGTH_LONG).show();
        this.finish();

    }

    public void atualizaProduto(){
        String text = "";
        for(Produto produto:Controller.getInstance().retornaProduto()){
            text += "Codigo: "+produto.getCodigo()+"Descrição"+produto.getDescricao()+
                    "\nValor Unitário:"+produto.getValorUnit()+"\n"+
                    "-------------------------------------\n";
        }
        mostraProduto.setText(text);
    }
}