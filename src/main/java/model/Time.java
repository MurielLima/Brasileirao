/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Muriel
 */
public class Time {

    private String nome;
    private int vitorias = 0;
    private int derrotas = 0;
    private int empates = 0;
    private int golPro = 0;
    private int golContra = 0;
    private Integer clas = 0;
    private List<Jogo> jogos = new ArrayList<Jogo>();

    public Time() {

    }

    public Time(String nome) {
        this.nome = nome;
    }

    public Time(String nome, int vitorias, int derrotas, int empates, int golPro, int golContra) {
        this.nome = nome;
        this.vitorias = vitorias;
        this.derrotas = derrotas;
        this.empates = empates;
        this.golPro = golPro;
        this.golContra = golContra;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getVitorias() {
        return vitorias;
    }

    public void addVitorias() {
        this.vitorias += 1;
    }

    public int getDerrotas() {
        return derrotas;
    }

    public void addDerrotas() {
        this.derrotas += 1;
    }

    public int getEmpates() {
        return empates;
    }

    public void addEmpates() {
        this.empates += 1;
    }

    public int getGolPro() {
        return golPro;
    }

    public void addGolPro(int gols) {
        this.golPro += gols;
    }

    public int getGolContra() {
        return golContra;
    }

    public void addGolContra(int gols) {
        this.golContra += gols;
    }

    public Integer getClas() {
        return clas;
    }

    public void setClas(Integer clas) {
        this.clas = clas;
    }

    public int getSaldoGols() {
        return golPro - golContra;
    }

    public int getPontos() {
        return (vitorias * 3) + empates;
    }

    public int getPartidas() {
        return vitorias + derrotas + empates;
    }

    public List<Jogo> getJogos() {
        return jogos;
    }

    public void setJogos(List<Jogo> jogos) {
        this.jogos = jogos;
    }

    @Override
    public String toString() {
        return "Time{" + "nome=" + nome + ", vitorias=" + vitorias + ", derrotas=" + derrotas + ", empates=" + empates + ", golPro=" + golPro + ", golContra=" + golContra + ", clas=" + clas + ", jogos=" + jogos + '}';
    }

}
