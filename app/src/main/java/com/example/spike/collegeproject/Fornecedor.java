package com.example.spike.collegeproject;

public class Fornecedor {


    private String nome;
    private String tipo;
    private String descricao;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    private int id;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString(){
        return "Id: " + getId() + " \nTitulo: " + getNome() + "\nTipo: " + getTipo() + " \nDescrição:" + getDescricao();
    }
}