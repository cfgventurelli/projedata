/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package br.com.cfgventurelli.src;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author cfgventurelli
 */
public class Principal {

    private static final NumberFormat NUMBER_FORMATTER = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final double SALARIO_MINIMO = 1212.00;
    
    private static List<Funcionario> funcionarios;
    
    public static void main(String[] args) {
        inserirFuncionarios();

        imprimirTitulo("Funcionários inseridos");
        imprimirFuncionarios();
        System.out.println("\n");
        
        removerJoao();
        imprimirTitulo("João removido");
        imprimirFuncionarios();
        System.out.println("\n");
        
        aumentarSalario();
        imprimirTitulo("Aumento salarial de 10%");
        imprimirFuncionarios();
        System.out.println("\n");

        imprimirTitulo("Funcionários agrupados por função");
        agruparPorFuncao();
        System.out.println("");
        
        imprimirTitulo("Aniversariantes de Outubro e Dezembro");
        imprimirFuncionariosAniversario10e12();
        System.out.println("\n");
        
        imprimirTitulo("Funcionário mais velho");
        imprimirFuncionárioMaiorIdade();
        System.out.println("\n");

        imprimirTitulo("Funcionários por ordem alfabética");
        imprimirOrdemAlfabética();
        System.out.println("\n");
        
        imprimirTitulo("Total dos salários de todos os funcionários");
        imprimirTotalSalarios();
        System.out.println("\n");

        imprimirTitulo("Quantidade de salários mínimo por funcionário");
        imprimirQtdSalariosMinimos();
        System.out.println("\n");
    }
    
    private static void imprimirTitulo(String texto) {
        System.out.println(texto);
        System.out.println("--------------------------------------------------------------------------------");
    }
    
    private static void inserirFuncionarios() {
        funcionarios = new ArrayList<>();
        
        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal(2009.44), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal(2284.38), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal(9836.11), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal(19119.88), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal(2234.68), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal(1582.72), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal(4071.84), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal(3017.45), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, 05, 24), new BigDecimal(1606.85), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal(2799.93), "Gerente"));
    }
    
    private static void removerJoao() {
        funcionarios.removeIf(f -> f.getNome().equals("João"));
    }
    
    private static void imprimirFuncionario(Funcionario f) {
        System.out.printf(
                "Nome: %s;\tNascimento: %s;\tSalário: %s;\tFunção: %s;\n",
                f.getNome(),
                f.getDataNascimento().format(DATE_FORMATTER),
                NUMBER_FORMATTER.format(f.getSalario()),
                f.getFuncao());
    }
    
    private static void imprimirFuncionarios() {        
        for (Funcionario f : funcionarios) {
            imprimirFuncionario(f);
        }
    }
    
    private static void aumentarSalario() {
        for (Funcionario f : funcionarios) {
            f.setSalario(f.getSalario().multiply(new BigDecimal(1.1)));
        }
    }
    
    private static void agruparPorFuncao() {
        Map<String, List<Funcionario>> m = funcionarios
                .stream()
                .collect(Collectors.groupingBy(f -> f.getFuncao()));
        
        for (Map.Entry<String, List<Funcionario>> e : m.entrySet()) {
            System.out.printf("%s:\n", e.getKey());
            for (Funcionario f : e.getValue()){
                imprimirFuncionario(f);
            }
            System.out.println("");
        }
    }
    
    private static void imprimirFuncionariosAniversario10e12() {
        List<Funcionario> aniversariantes;
        aniversariantes = funcionarios
                .stream()
                .filter(f -> f.getDataNascimento().getMonth() == Month.OCTOBER || f.getDataNascimento().getMonth() == Month.DECEMBER)
                .collect(Collectors.toList());
         
        for (Funcionario f : aniversariantes) {
            imprimirFuncionario(f);
        }
    }
    
    private static void imprimirFuncionárioMaiorIdade() {
        Funcionario f = funcionarios
                .stream()
                .min(Comparator.comparing(Funcionario::getDataNascimento))
                .get();

        long idade = ChronoUnit.YEARS.between(f.getDataNascimento(), LocalDate.now());

        System.out.printf("Nome: %s; Idade: %d;\n", f.getNome(), idade);
    }
    
    private static void imprimirOrdemAlfabética() {
        funcionarios = funcionarios
                .stream()
                .sorted(Comparator.comparing(f -> f.getNome()))
                .collect(Collectors.toList());
        
        imprimirFuncionarios();
    }
    
    private static void imprimirTotalSalarios() {
        long total = funcionarios
                .stream()
                .mapToLong(f -> f.getSalario().longValue())
                .sum();
        
        System.out.println(NUMBER_FORMATTER.format(total));
    }
    
    private static void imprimirQtdSalariosMinimos() {
        for (Funcionario f : funcionarios) {
            System.out.printf(
                    "%s recebe %.1f salários mínimos\n",
                    f.getNome(),
                    f.getSalario().doubleValue() / SALARIO_MINIMO);
        }
    }
}
