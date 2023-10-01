package com.example.syz.models;

public class Produto {

    private String descricao;
    private String codigo;
    private double valorUnit;

    public Produto(String descricao, String codigo, double valorUnit) {
        this.descricao = descricao;
        this.codigo = codigo;
        this.valorUnit = valorUnit;
    }

    public Produto() {
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public double getValorUnit() {
        return valorUnit;
    }

    public void setValorUnit(double valorUnit) {
        this.valorUnit = valorUnit;
    }
}
