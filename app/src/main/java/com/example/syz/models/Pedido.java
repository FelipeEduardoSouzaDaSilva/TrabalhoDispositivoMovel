package com.example.syz.models;

import java.util.ArrayList;

public class Pedido {
    private int codigoPedido;
    private ArrayList<Cliente> listaCliente;
    private ArrayList<Produto> listaProduto;
    private double qtd;
    private String cliente;
    private double valorTotal;

    public Pedido(int codigoPedido, ArrayList<Cliente> listaCliente, ArrayList<Produto> listaProduto, double qtd,String cliente, double valorTotal) {
        this.codigoPedido = codigoPedido;
        this.listaCliente = listaCliente;
        this.listaProduto = listaProduto;
        this.qtd = qtd;
        this.valorTotal= valorTotal;
        this.cliente = cliente;
    }

    public Pedido() {
    }

    public int getCodigoPedido() {
        return codigoPedido;
    }

    public void setCodigoPedido(int codigoPedido) {
        this.codigoPedido = codigoPedido;
    }

    public ArrayList<Cliente> getListaCliente() {
        return listaCliente;
    }

    public void setListaCliente(ArrayList<Cliente> listaCliente) {
        this.listaCliente = listaCliente;
    }

    public ArrayList<Produto> getListaProduto() {
        return listaProduto;
    }

    public void setListaProduto(ArrayList<Produto> listaProduto) {
        this.listaProduto = listaProduto;
    }

    public double getQtd() {
        return qtd;
    }

    public void setQtd(double qtd) {
        this.qtd = qtd;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }
}
