
package com.ti2cc;

public class Esporte {
    private int numeroJogadores;
    private String nome;
    private String dataCriacao;
    
    public Esporte() {
        this.numeroJogadores = 0;
        this.nome = "";
        this.dataCriacao = "";
    }
    
    public Esporte(int numeroJogadores, String nome, String dataCriacao) {
        this.numeroJogadores = numeroJogadores;
        this.nome = nome;
        this.dataCriacao = dataCriacao;
    }

    public int getNumeroJogadores() {
        return numeroJogadores;
    }

    public void setNumeroJogadores(int numeroJogadores) {
        this.numeroJogadores = numeroJogadores;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(String dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    @Override
    public String toString() {
        return "Esporte [numeroJogadores=" + numeroJogadores + ", nome=" + nome + ", dataCriacao=" + dataCriacao + "]";
    }   
}
