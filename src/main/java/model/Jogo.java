/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Murie
 */
public class Jogo {

    private String timeA;
    private String timeB;
    private byte golA;
    private byte golB;

    public Jogo(String timeA, String timeB, byte golA, byte golB) {
        this.timeA = timeA;
        this.timeB = timeB;
        this.golA = golA;
        this.golB = golB;
    }

    public Jogo(String linha) {
        String[] partes = linha.split("\\,");   // Dividir a string em partes. 
        this.timeA = partes[0];

        this.golA = Byte.parseByte(partes[1]);

        this.timeB = partes[2];

        this.golB = Byte.parseByte(partes[3]);

    }

    public String getTimeA() {
        return timeA;
    }

    public void setTimeA(String timeA) {
        this.timeA = timeA;
    }

    public String getTimeB() {
        return timeB;
    }

    public void setTimeB(String timeB) {
        this.timeB = timeB;
    }

    public byte getGolA() {
        return golA;
    }

    public void setGolA(byte golA) {
        this.golA = golA;
    }

    public byte getGolB() {
        return golB;
    }

    public void setGolB(byte golB) {
        this.golB = golB;
    }

    @Override
    public String toString() {
        return "Jogo{" + "timeA=" + timeA + ", timeB=" + timeB + ", golA=" + golA + ", golB=" + golB + '}';
    }

}
