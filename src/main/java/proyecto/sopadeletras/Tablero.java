package proyecto.sopadeletras;

import java.util.*;

public class Tablero {

    private Diccionario dic;
    private ArrayList<String> palabras;
    private char[][] tablero;
    private boolean[][] ocupados;
    private int tamaño;
    private boolean[][] encontradas;
    
    //CONSTRUCTOR
    public Tablero(int t) {
        tamaño = t;
        dic = new Diccionario();
        palabras = new ArrayList<>();
        tablero = new char[t][t];
        ocupados = new boolean[t][t];
        encontradas = new boolean[t][t];
    }
    
    //GENERAR EL TABLERO DE CHARS AL AZAR
    public void generarTablero() {
        Random rnd = new Random();
        char letra = '.';
        for (int i = 0; i < tamaño; i++) {
            for (int j = 0; j < tamaño; j++) {
                letra = Character.toUpperCase((char) (rnd.nextInt(26) + 'a'));
                tablero[i][j] = letra;
                ocupados[i][j] = false;
                encontradas[i][j] = false;
            }
        }
        System.out.println("~~Tablero generado~~");
    }
    
    // IMPRIMIR EL TABLERO EN CONSOLA 
    public void mostrarTablero() {
        for (int i = 0; i < tamaño; i++) {
            for (int j = 0; j < tamaño; j++) {
                System.out.print(tablero[i][j] + " ");
            }
            System.out.println("\n");
        }
    }
    
    //MOSTRAR MATRIZ DE LETRAS OCUPADAS (PARA CONTROL DE LLENADO DE PALABRAS)
    public void mostrarOcupados() {
        System.out.println("\n\n~~ocupados~~\n");
        for (int i = 0; i < tamaño; i++) {
            for (int j = 0; j < tamaño; j++) {
                if (ocupados[i][j] == false) {
                    System.out.print("* ");
                } else {
                    System.out.print("- ");
                }
            }
            System.out.println("\n");
        }
    }

    //MOSTRAR MATRIZ DE PALARBAS ENCONTRADAS (PARA CONTROL DE BUSQUEDA DE PALABRAS)
    public void mostrarEncontrados() {
        System.out.println("\n\n~~Encontrados~~\n");
        for (int i = 0; i < tamaño; i++) {
            for (int j = 0; j < tamaño; j++) {
                if (encontradas[i][j] == false) {
                    System.out.print("* ");
                } else {
                    System.out.print("- ");
                }
            }
            System.out.println("\n");
        }
    }
    // OBTENER LISTA DE PALARBAS AL AZAR DEL DICCIONARIO
    public ArrayList<String> getPalabras(int nivel, int tipo) {
        ArrayList<String> palabras = new ArrayList<>();
        String[] obtenidas = new String[25];
        switch (nivel) {
            case 1:
                obtenidas = dic.darPalabras(tipo, 8);
                break;
            case 2:
                obtenidas = dic.darPalabras(tipo, 16);
                break;
            case 3:
                obtenidas = dic.darPalabras(tipo, 20);
                break;
            case 4:
                obtenidas = dic.darPalabras(tipo, 25);
                break;
            default:
                System.out.println("error");
                break;
        }
        for (int i = 0; i < obtenidas.length; i++) {
            palabras.add(obtenidas[i]);
        }
        this.palabras = palabras;
        System.out.println("~~Palabras obtenidas~~");
        return palabras;
    }
    
    //MOSTRAR LISTA DE PALABRAS DEL JUEGO EN CONSOLA
    public void mostrarPalabras() {
        for (String string : palabras) {
            System.out.println("° " + string);
        }
    }
    
    //LLENAR EL TABLERO CON LAS PALBRAS OBTENIDAS EN POSICIONES AL AZAR
    public void llenarPalabras() {
        Random rnd = new Random();
        int agregadas = 0;
        do {
            int modo = rnd.nextInt(8);
            String palabra = palabras.get(agregadas);
            int palSIZE = palabra.length();
            //System.out.print("¬¬ " + palabra);
            //modo = 0;
            int xRND = 0;
            int yRND = 0;
            int contador = 0;
            //AGREGAR ORIENTACION Y POSICION A LA PALABRA DENTRO DEL TABLERO
            switch (modo) {
                case 0: // HORIZONTAL
                    xRND = rnd.nextInt(tamaño - palSIZE);
                    yRND = rnd.nextInt(tamaño);
                    ////System.out.println("<<<" + yRND + " " + xRND + " H >>>");
                    for (int i = 0; i < palabra.length(); i++) {
                        if (ocupados[yRND][xRND + i] == false || palabra.charAt(i) == tablero[yRND][xRND + i]) {
                            contador++;
                        }
                    }
                    if (contador == palabra.length()) {
                        agregadas++;
                        for (int i = 0; i < palabra.length(); i++) {
                            tablero[yRND][xRND + i] = palabra.charAt(i);
                            ocupados[yRND][xRND + i] = true;

                        }
                    } else {
                        //System.out.println("NO SE PUDOO AGREAGR");
                    }
                    break;
                case 1: // VERTICAL
                    xRND = rnd.nextInt(tamaño);
                    yRND = rnd.nextInt(tamaño - palSIZE);
                    //System.out.println("<<<" + yRND + " " + xRND + " V >>>");
                    for (int i = 0; i < palabra.length(); i++) {
                        if (ocupados[yRND + i][xRND] == false || palabra.charAt(i) == tablero[yRND + i][xRND]) {
                            contador++;
                        }
                    }
                    if (contador == palabra.length()) {
                        agregadas++;
                        for (int i = 0; i < palabra.length(); i++) {
                            tablero[yRND + i][xRND] = palabra.charAt(i);
                            ocupados[yRND + i][xRND] = true;

                        }
                    } else {
                        //System.out.println("NO SE PUDOO AGREAGR");
                    }
                    break;
                case 2: // DIAGONAL ++
                    xRND = rnd.nextInt(tamaño - palSIZE);
                    yRND = rnd.nextInt(tamaño - palSIZE);
                    //System.out.println("<<<" + yRND + " " + xRND + " D + + >>>");
                    for (int i = 0; i < palabra.length(); i++) {
                        if (ocupados[yRND + i][xRND + i] == false || palabra.charAt(i) == tablero[yRND + i][xRND + i]) {
                            contador++;
                        }
                    }
                    if (contador == palabra.length()) {
                        agregadas++;
                        for (int i = 0; i < palabra.length(); i++) {
                            tablero[yRND + i][xRND + i] = palabra.charAt(i);
                            ocupados[yRND + i][xRND + i] = true;

                        }
                    } else {
                        //System.out.println("NO SE PUDOO AGREAGR");
                    }
                    break;
                case 3: // VERTICAL INVERTIDA
                    yRND = (tamaño - 1) - rnd.nextInt(tamaño - palSIZE);
                    xRND = rnd.nextInt(tamaño);
                    //System.out.println("<<<" + yRND + " " + xRND + " V - >>>");
                    for (int i = 0; i < palabra.length(); i++) {
                        if (ocupados[yRND - i][xRND] == false || palabra.charAt(i) == tablero[yRND - i][xRND]) {
                            contador++;
                        }
                    }
                    if (contador == palabra.length()) {
                        agregadas++;
                        for (int i = 0; i < palabra.length(); i++) {
                            tablero[yRND - i][xRND] = palabra.charAt(i);
                            ocupados[yRND - i][xRND] = true;

                        }
                    } else {
                        //System.out.println("NO SE PUDOO AGREAGR");
                    }
                    break;
                case 4: // HORIZONTAL INVERTIDA
                    yRND = rnd.nextInt(tamaño);
                    xRND = (tamaño - 1) - rnd.nextInt(tamaño - palSIZE);
                    //System.out.println("<<<" + yRND + " " + xRND + " H - >>>");
                    for (int i = 0; i < palabra.length(); i++) {
                        if (ocupados[yRND][xRND - i] == false || palabra.charAt(i) == tablero[yRND][xRND - i]) {
                            contador++;
                        }
                    }
                    if (contador == palabra.length()) {
                        agregadas++;
                        for (int i = 0; i < palabra.length(); i++) {
                            tablero[yRND][xRND - i] = palabra.charAt(i);
                            ocupados[yRND][xRND - i] = true;

                        }
                    } else {
                        //System.out.println("NO SE PUDOO AGREAGR");
                    }
                    break;
                case 5: // DIAGONAL - - 
                    yRND = (tamaño - 1) - rnd.nextInt(tamaño - palSIZE);
                    xRND = (tamaño - 1) - rnd.nextInt(tamaño - palSIZE);
                    //System.out.println("<<<" + yRND + " " + xRND + " D - ->>>");
                    for (int i = 0; i < palabra.length(); i++) {
                        if (ocupados[yRND - i][xRND - i] == false || palabra.charAt(i) == tablero[yRND - i][xRND - i]) {
                            contador++;
                        }
                    }
                    if (contador == palabra.length()) {
                        agregadas++;
                        for (int i = 0; i < palabra.length(); i++) {
                            tablero[yRND - i][xRND - i] = palabra.charAt(i);
                            ocupados[yRND - i][xRND - i] = true;

                        }
                    } else {
                        //System.out.println("NO SE PUDOO AGREAGR");
                    }
                    break;
                case 6: // DIAGONAL + - 
                    yRND = rnd.nextInt(tamaño - palSIZE);
                    xRND = (tamaño - 1) - rnd.nextInt(tamaño - palSIZE);
                    //System.out.println("<<<" + yRND + " " + xRND + " D + ->>>");
                    for (int i = 0; i < palabra.length(); i++) {
                        if (ocupados[yRND + i][xRND - i] == false || palabra.charAt(i) == tablero[yRND + i][xRND - i]) {
                            contador++;
                        }
                    }
                    if (contador == palabra.length()) {
                        agregadas++;
                        for (int i = 0; i < palabra.length(); i++) {
                            tablero[yRND + i][xRND - i] = palabra.charAt(i);
                            ocupados[yRND + i][xRND - i] = true;

                        }
                    } else {
                        //System.out.println("NO SE PUDOO AGREAGR");
                    }
                    break;
                case 7: // DIAGONAL - + 
                    yRND = (tamaño - 1) - rnd.nextInt(tamaño - palSIZE);
                    xRND = rnd.nextInt(tamaño - palSIZE);
                    //System.out.println("<<<" + yRND + " " + xRND + " D - +>>>");
                    for (int i = 0; i < palabra.length(); i++) {
                        if (ocupados[yRND - i][xRND + i] == false || palabra.charAt(i) == tablero[yRND - i][xRND + i]) {
                            contador++;
                        }
                    }
                    if (contador == palabra.length()) {
                        agregadas++;
                        for (int i = 0; i < palabra.length(); i++) {
                            tablero[yRND - i][xRND + i] = palabra.charAt(i);
                            ocupados[yRND - i][xRND + i] = true;

                        }
                    } else {
                        //System.out.println("NO SE PUDOO AGREAGR");
                    }
                    break;
            }
            //System.out.println("AGREAGR " + agregadas);
        } while (agregadas < palabras.size());

    }

    //BUSCAR UNA PALABRA ENTRE LOS CARATERES DEL TABLERO 
    public boolean buscar(int x, int y, int dir, String recibida) {
        recibida = recibida.toUpperCase().trim();
        String buscada = "";
        int xInicio = x;
        int yInicio = y;
        int[] posicionesX = new int[15];
        int[] posicionesY = new int[15];
        int largo = recibida.length();
        boolean encontrada = false;
        //BUSCAR PALABRA EN CIERTA DIRECCION
        switch (dir) {
            case 0:
                for (int i = 0; i < largo; i++) {
                    try {
                        buscada = buscada + tablero[yInicio][xInicio + i];
                        posicionesX[i] = xInicio + i;
                        posicionesY[i] = yInicio;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("LIMITE DE TABLA");
                    }
                }
                break;

            case 1:
                for (int i = 0; i < recibida.length(); i++) {
                    try {
                        buscada = buscada + tablero[yInicio + i][xInicio];
                        posicionesX[i] = xInicio;
                        posicionesY[i] = yInicio + i;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("LIMITE DE TABLA");
                    }
                }
                break;
            case 2:
                for (int i = 0; i < recibida.length(); i++) {
                    try {
                        buscada = buscada + tablero[yInicio + i][xInicio + i];
                        posicionesX[i] = xInicio + i;
                        posicionesY[i] = yInicio + i;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("LIMITE DE TABLA");
                    }
                }
                break;
            case 3:
                for (int i = 0; i < recibida.length(); i++) {
                    try {
                        buscada = buscada + tablero[yInicio - i][xInicio];
                        posicionesX[i] = xInicio;
                        posicionesY[i] = yInicio - i;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("LIMITE DE TABLA");
                    }
                }
                break;
            case 4:
                for (int i = 0; i < recibida.length(); i++) {
                    try {
                        buscada = buscada + tablero[yInicio][xInicio - i];
                        posicionesX[i] = xInicio - i;
                        posicionesY[i] = yInicio;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("LIMITE DE TABLA");
                    }
                }
                break;
            case 5:
                for (int i = 0; i < recibida.length(); i++) {
                    try {
                        buscada = buscada + tablero[yInicio - i][xInicio - i];
                        posicionesX[i] = xInicio - i;
                        posicionesY[i] = yInicio - i;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("LIMITE DE TABLA");
                    }
                }
                break;
            case 6:
                for (int i = 0; i < recibida.length(); i++) {
                    try {
                        buscada = buscada + tablero[yInicio + i][xInicio - i];
                        posicionesX[i] = xInicio - i;
                        posicionesY[i] = yInicio + i;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("LIMITE DE TABLA");
                    }
                }
                break;
            case 7:
                for (int i = 0; i < recibida.length(); i++) {
                    try {
                        buscada = buscada + tablero[yInicio - i][xInicio + i];
                        posicionesX[i] = xInicio + i;
                        posicionesY[i] = yInicio - i;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("LIMITE DE TABLA");
                    }
                }
                break;
            default:
                System.out.println("Direccion no valida");
                break;
        }
        System.out.println("Busqueda: " + buscada);
        if (recibida.equals(buscada)) {
            encontrada = true;
            System.out.println("Se encontro la palabra: "+buscada);
            for (int i = 0; i < largo; i++) {
                encontradas[posicionesY[i]][posicionesX[i]] = true;
            }
            //mostrarEncontrados();
        }
        return encontrada;
    }
    
    //GETTERS
    public char[][] getTablero(){
        return tablero;
    }
    public boolean[][] getOcupadas(){
        return ocupados;
    }
    public boolean[][] getEncontradas(){
        return encontradas;
    }
}
