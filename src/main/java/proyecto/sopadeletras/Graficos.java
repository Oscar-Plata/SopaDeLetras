/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.sopadeletras;

/**
 * CLASE DE lA VENTANA GRAFICA DEL JUEGO
 * @author OScar Joel Lopez Plata
 * @version 02/12/2021
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.*;

public class Graficos {

    //VARIABLES INICALES
    char[][] letras;
    Label[][] labels;
    int tamaño;
    int largoLista;
    Label[] palabras;
    boolean[][] pintados;
    boolean[] pintadosLista;
    Frame f = new Frame("SOPA DE LETRAS (Oscar's Version)");
    Color c1 = new Color(220, 210, 146);
    Color c2 = new Color(24, 118, 100);
    Color c3 = new Color(32, 108, 162);
    Color c0 = new Color(30, 33, 48);
    Font letraFont = new Font("Rockwell Nova", Font.BOLD, 25);
    Font letraFont2 = new Font("Rockwell Nova", Font.BOLD, 20);
    Font numFONT = new Font("Rockwell Nova", Font.PLAIN, 15);
    Label[] tiempos;

    public Graficos() {
    }
    
    //CONSTRUCTOR
    public Graficos(int tamaño, char[][] letras, int cantidadPalabras) {
        this.letras = letras;
        this.tamaño = tamaño;
        f.setSize(900, 800);
        f.setLayout(null);
        f.setVisible(true);
        f.setBackground(c1);
        labels = new Label[tamaño][tamaño];
        largoLista = cantidadPalabras;
        palabras = new Label[cantidadPalabras];
        pintados = new boolean[tamaño][tamaño];
        pintadosLista = new boolean[cantidadPalabras];
        f.addWindowListener(new MyWindowListener());
        tiempos=new Label[2];
    }
    
    //GENERAR LA MATRIZ DE CARACTERES RECIBIDOS EN EL ARRELGO
    public void generarLetras() {
        int x = 30;
        int y = 30;
        Label[] xView = new Label[tamaño];
        Label[] yView = new Label[tamaño];
        for (int i = 0; i < tamaño; i++) {
            //AGREGAR COLUMNA Y LINEA NUMERIA DE REFERNECIA
            xView[i] = new Label(String.valueOf(i + 1));
            xView[i].setBounds(x + (i * 30), 30, 30, 30);
            xView[i].setAlignment(Label.CENTER);
            xView[i].setFont(numFONT);
            xView[i].setBackground(c2);
            xView[i].setForeground(Color.white);
            f.add(xView[i]);
            yView[i] = new Label(String.valueOf(i + 1));
            yView[i].setBounds(0, y + 30 + (30 * i), 30, 30);
            yView[i].setAlignment(Label.CENTER);
            yView[i].setFont(numFONT);
            yView[i].setBackground(c3);
            yView[i].setForeground(Color.white);
            f.add(yView[i]);
            
            //DIBUJAR LETRA
            for (int j = 0; j < tamaño; j++) {
                labels[i][j] = new Label(letras[j][i] + "");
                labels[i][j].setBounds(x + (i * 30), y + 30 + (j * 30), 30, 30);
                labels[i][j].setBackground(c1);
                labels[i][j].setFont(letraFont);
                labels[i][j].setAlignment(Label.CENTER);
                labels[i][j].setForeground(c0);
                f.add(labels[i][j]);
            }
        }
    }
    
    //DIBUJAR LISTA DE PALABRAS A BUSCAR
    public void generarLista(ArrayList<String> palabrasAL) {
        for (int i = 0; i < largoLista; i++) {
            palabras[i] = new Label(palabrasAL.get(i));
            palabras[i].setForeground(c0);
            palabras[i].setBackground(c1);
            palabras[i].setAlignment(Label.LEFT);
            palabras[i].setBounds(700, 45 + (i * 25), 200, 25);
            palabras[i].setFont(letraFont2);
            f.add(palabras[i]);
        }
    }
    
    //PINTAR EL COLOR DE LAS PALABRAS ENCONTRADAS EN MATRIZ Y LISTA
    public void pintarAciertos(boolean[][] matriz, int[] lista) {
        Random rnd = new Random();
        int rndInt = rnd.nextInt(5);
        Color colorRnd = getColor(rndInt);
        for (int i = 0; i < tamaño; i++) {
            for (int j = 0; j < tamaño; j++) {
                if (matriz[i][j] == true && pintados[j][i] == false) {
                    labels[j][i].setBackground(colorRnd);
                    pintados[j][i] = true;
                }
            }
        }
        for (int i = 0; i < largoLista; i++)
            try {
            if (pintadosLista[i] == false) {
                palabras[lista[i]].setBackground(colorRnd);
                pintadosLista[i] = true;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            // System.out.println("a");
        }
    }
    //IMPRIMIR EL TIEMPO E JUEGO
    public void pintarTiempos(String[] t){
        tiempos[0]=new Label("TIEMPO LIMTE: "+t[0]);
        tiempos[1]=new Label("TIEMPO ACTUAL: "+t[1]);
            tiempos[0].setForeground(c0);
            tiempos[0].setBackground(c1);
            tiempos[0].setAlignment(Label.LEFT);
            tiempos[0].setBounds(25,600 , 500, 25);
            tiempos[0].setFont(letraFont2);
            tiempos[1].setForeground(c0);
            tiempos[1].setBackground(c1);
            tiempos[1].setAlignment(Label.LEFT);
            tiempos[1].setBounds(25,650 , 500, 25);
            tiempos[1].setFont(letraFont2);
            tiempos[0].setText("TIEMPO LIMTE: "+t[0]);
            tiempos[1].setText("TIEMPO ACTUAL: "+t[1]);
            f.add(tiempos[0]);
            f.add(tiempos[1]);
    }
    
    //OBTENER COLOR AL AZAR PARA PINTAR ENCONTRADAS
    public Color getColor(int x) {
        Color color = Color.black;
        switch (x) {
            case 0:
                color = Color.red;
                break;
            case 1:
                color = Color.blue;
                break;
            case 2:
                color = Color.green;
                break;
            case 3:
                color = Color.yellow;
                break;
            case 4:
                color = Color.orange;
                break;
        }
        return color;
    }
    
    //GETTERS Y SETTERS
    public char[][] getLetras() {
        return letras;
    }

    public void setLetras(char[][] letras) {
        this.letras = letras;
    }

    public int getTamaño() {
        return tamaño;
    }

    public void setTamaño(int tamaño) {
        this.tamaño = tamaño;
    }
    
    
    //METODO PARA TERMIAR PROGRAMA AL PULSAR BOTON X EN LA VENTANA GRAFICA
    static class MyWindowListener extends WindowAdapter {

        public void windowClosing(WindowEvent e) {
            System.exit(0);
        }
    }
}
