/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.sopadeletras;

/**
 * CLASE RELOJ, CONTROL DE TIEMPO INICIAL Y ACTUAL DEL JUEGO
 * @author OScar Joel Lopez Plata
 * @version 02/12/2021
 */
public class Reloj {

    long tiempoInicio;
    long tiempoActual;
    float tiempoJuego;
    float tiempoMeta;

    public Reloj() {
    }

    public Reloj(double meta) {
        tiempoMeta = (float) meta;
        tiempoInicio = 0;
        tiempoActual = 0;
        tiempoJuego = 0;
    }

    public long tActual() {
        tiempoActual = System.currentTimeMillis();
        return tiempoActual;
    }

    public void setIncio() {
        tiempoInicio = System.currentTimeMillis();
    }

    public long getInicio() {
        return tiempoInicio;
    }

    public float tJuego() {
        tiempoJuego = (float) ((tActual() - getInicio()) / 1000);
        return tiempoJuego;
    }

    public void setMeta(float tiempoMin) {
        tiempoMeta = tiempoMin;
    }

    public float getMeta() {
        return tiempoMeta;
    }

    public String[] imprimir() {
        String[] tiempos = new String[2];
        int metaMin = (int) tiempoMeta;
        int metaSeg = (int) ((tiempoMeta - metaMin) * 60);
        int actuMin = (int) tiempoJuego / 60;
        int actuSeg = (int) tiempoJuego % 60;
        tiempos[0] = String.valueOf(metaMin) + ":" + String.format("%02d", metaSeg);
        tiempos[1] = String.valueOf(actuMin) + ":" + String.format("%02d", actuSeg);
        System.out.println("TIEMPO LIMITE: "+tiempos[0]);
        System.out.println("TIEMPO ACTUAL: "+tiempos[1]);
        return tiempos;
    }

}
