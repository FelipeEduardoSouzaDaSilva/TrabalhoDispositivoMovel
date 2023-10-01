package com.example.syz;

import com.example.syz.models.Cliente;
import com.example.syz.models.Pedido;
import com.example.syz.models.Produto;

import java.util.ArrayList;

public class Controller {

    private static Controller instancia;
    private static ArrayList<Cliente> listaCliente;
    private static ArrayList<Produto> listaProduto;
    private static ArrayList<Pedido> listaPedido;

    public static Controller getInstance(){
        if(instancia == null) {
            return instancia = new Controller();
        }else {
            return instancia;
        }
    }

    public Controller() {
        listaPedido = new ArrayList<>();
        listaProduto = new ArrayList<>();
        listaCliente = new ArrayList<>();
    }


    public Pedido buscarPedidoPorCodigo(int codigo) {
        for (Pedido pedido : listaPedido) {
            if (pedido.getCodigoPedido() == codigo) {
                return pedido;
            }
        }
        return null; // Retorna null se o pedido não for encontrado
    }

    public void salvarPedido(Pedido pedido){
        listaPedido.add(pedido);
    }
    public ArrayList<Pedido> retornaPedido(){
        return listaPedido;
    }
    public void salvarProduto(Produto produto){
        listaProduto.add(produto);
    }
    public ArrayList<Produto> retornaProduto(){
        return listaProduto;
    }
    public void salvarCliente(Cliente cliente){
        listaCliente.add(cliente);
    }
    public ArrayList<Cliente> retornaCliente(){
        return listaCliente;
    }
}
