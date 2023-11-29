/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.cfgventurelli.src;

import java.time.LocalDate;

/**
 *
 * @author cfgventurelli
 */
public class Pessoa {
    private String nome;
    private LocalDate dataNascimento;
    
    Pessoa(String nome, LocalDate dataNascimento) {
        this.setNome(nome);
        this.setDataNascimento(dataNascimento);
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return this.nome;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public LocalDate getDataNascimento() {
        return this.dataNascimento;
    }
}
