/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.cfgventurelli.src;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author cfgventurelli
 */
public class Funcionario extends Pessoa {
    private BigDecimal salario;
    private String funcao;
    
    Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
        super(nome, dataNascimento);
        this.setSalario(salario);
        this.setFuncao(funcao);
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public BigDecimal getSalario() {
        return this.salario;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }
    
    public String getFuncao() {
        return this.funcao;
    }   
}
