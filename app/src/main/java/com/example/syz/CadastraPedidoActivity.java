package com.example.syz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.syz.models.Cliente;
import com.example.syz.models.Pedido;
import com.example.syz.models.Produto;

import java.util.ArrayList;

public class CadastraPedidoActivity extends AppCompatActivity {

    private RadioGroup rgModoPgt;
    private RadioButton rbVista;
    private RadioButton rbPrazo;
    private AutoCompleteTextView acClientes;
    private AutoCompleteTextView acProdutos;
    private EditText edQtdProduto;
    private EditText edParcelas;
    private Button addProduto;
    private Button finalizar;
    private Button calculaPedido;
    private TextView tvProdutoPedido;
    private TextView tvValor;
    private TextView tvParcelas;
    private double valorTotal;
    private double valorTotalProdutos;
    private double parcela;
    private static int codigoPedido = 1;
    private String produtoInfo ="";
    private ArrayList<Produto> listaProduto;
    private ArrayList<Cliente> listaCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastra_pedido);
        setTitle("Pedidos");

        rgModoPgt = findViewById(R.id.rgModoPgt);
        rbVista = findViewById(R.id.rbVista);
        rbPrazo = findViewById(R.id.rbPrazo);
        acClientes = findViewById(R.id.acCliente);
        acProdutos = findViewById(R.id.acProduto);
        edQtdProduto = findViewById(R.id.edQtdProduto);
        edParcelas = findViewById(R.id.edParcelas);
        addProduto = findViewById(R.id.addProduto);
        calculaPedido = findViewById(R.id.calculaPedido);
        finalizar = findViewById(R.id.finalizar);
        tvProdutoPedido = findViewById(R.id.tvProdutoPedido);
        tvParcelas = findViewById(R.id.tvParcelas);
        tvValor = findViewById(R.id.tvValor);
        carregarClientes();
        carregarProdutos();

        rgModoPgt.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {


                if(i == rbPrazo.getId()){

                    tvParcelas.setVisibility(View.VISIBLE);
                    edParcelas.setVisibility(View.VISIBLE);


                }else{

                    tvParcelas.setVisibility(View.GONE);
                    edParcelas.setVisibility(View.GONE);



                }
            }

        });

        addProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                atualizaProduto();
            }
        });

        calculaPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                carregaValor();
                if(rbVista.isChecked()){
                    calculaVista();}
                if(rbPrazo.isChecked()){
                    calculaPrazo();
                }
            }
        });

        finalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finalizarPedido();
            }
        });

    }

    private void finalizarPedido() {

        String quantidade = edQtdProduto.getText().toString();

        if(acClientes.getText() == null){
            acClientes.setError("O campo deve ser preenchido");
        }
        if(acProdutos.getText() == null){
            acProdutos.setError("O campo deve ser prenchido");
        }

        Pedido pedido = new Pedido();
        pedido.setCodigoPedido(codigoPedido);
        pedido.setCliente(acClientes.getText().toString());
        pedido.setQtd(Integer.parseInt(quantidade));
        pedido.setValorTotal(valorTotalProdutos);
        Controller.getInstance().salvarPedido(pedido);

        Toast.makeText(this, "Pedido cadastrado com sucesso!!", Toast.LENGTH_LONG).show();
        this.finish();
    }

    private void calculaVista() {
        valorTotalProdutos = valorTotal-(valorTotal*0.05);

        tvValor.setText("Valor total do pedido: "+valorTotalProdutos);

    }

    private void calculaPrazo() {
        if (edParcelas.getText() != null && !edParcelas.getText().toString().isEmpty()) {
            valorTotalProdutos = valorTotal + (valorTotal * 0.05);
            parcela = valorTotalProdutos / Double.parseDouble(edParcelas.getText().toString());
            tvValor.setText("Nº de Parcelas: " + Double.parseDouble(edParcelas.getText().toString()) +
                    "\nValor das parcelas: " + parcela);
        } else {
            // Trate o caso em que edParcelas está vazio ou contém um valor não numérico.
            edParcelas.setError("O campo deve ser preenchido");
            // Pode exibir uma mensagem de erro para o usuário.
        }
    }

    private void carregaValor() {

        double aux = 0;
        double[]vetProdutos = new double[listaProduto.size()];

        for (int i = 0; i < listaProduto.size(); i++) {

            if(edQtdProduto.getText().toString()!= null){
                Produto produto = listaProduto.get(i);
                vetProdutos[i] = produto.getValorUnit();
                aux += vetProdutos[i] * Double.parseDouble(edQtdProduto.getText().toString());
            }else{
                edQtdProduto.setError("O campo deve ser preenchido");
            }

        }

        valorTotal = aux;



    }

    private void carregarProdutos() {

        listaProduto = Controller.getInstance().retornaProduto();
        String[]vetProduto = new String[listaProduto.size()];

        for (int i = 0; i < listaProduto.size(); i++) {

            Produto produto = listaProduto.get(i);
            vetProduto[i] = produto.getDescricao();

        }
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, vetProduto);
        acProdutos.setAdapter(adapter);
    }
    private void carregarClientes(){

      listaCliente = Controller.getInstance().retornaCliente();
      String[]vetCliente = new String[listaCliente.size()];

        for (int i = 0; i < listaCliente.size(); i++) {

            Cliente cliente = listaCliente.get(i);
            vetCliente[i] = cliente.getNome();

        }
        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, vetCliente);
        acClientes.setAdapter(adapter); 
    }

    private void atualizaProduto() {
        String quantidade = edQtdProduto.getText().toString();
        String produtoSelecionado = acProdutos.getText().toString();

        for (Produto produto : Controller.getInstance().retornaProduto()) {
            if (produto.getDescricao().equals(produtoSelecionado) && !quantidade.isEmpty()) {
                 produtoInfo += "Código do produto: " + produto.getCodigo() +
                        "\nProduto: " + produto.getDescricao() +
                        "\nValor: " + produto.getValorUnit() +
                        "\nQuantidade: " + quantidade +
                        "\n-------------------------------------\n";

                tvProdutoPedido.setText(produtoInfo);

                break;
            }
        }


    }
}