package com.example.syz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.syz.models.Pedido;

public class BuscaPedidoActivity extends AppCompatActivity {

    private EditText edPesquisa;
    private Button btPesquisa;
    private TextView tvPedidos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busca_pedido);

        edPesquisa = findViewById(R.id.edPesquisa);
        tvPedidos = findViewById(R.id.Pedidos);
        btPesquisa = findViewById(R.id.btPesquisa);

        btPesquisa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pesquisarPedido();
            }
        });
    }

    private void pesquisarPedido() {
        String codigoTexto = edPesquisa.getText().toString();
        if (!codigoTexto.isEmpty()) {
            int codigo = Integer.parseInt(codigoTexto);
            Pedido pedido = Controller.getInstance().buscarPedidoPorCodigo(codigo);

            if (pedido != null) {
                // Pedido encontrado, exiba as informações no TextView
                String textoPedido = "Código do Pedido: " + pedido.getCodigoPedido() +
                        "\nCliente: " + pedido.getCliente() +
                        "\nQuantidade: " + pedido.getQtd() +
                        "\nValor Total: " + pedido.getValorTotal();

                tvPedidos.setText(textoPedido);
            } else {
                // Pedido não encontrado, exiba uma mensagem indicando isso
                tvPedidos.setText("Pedido não encontrado");
            }
        } else {
            // Se o campo de pesquisa estiver vazio, exiba uma mensagem indicando isso
            tvPedidos.setText("Por favor, insira um código de pedido.");
        }
    }
}
