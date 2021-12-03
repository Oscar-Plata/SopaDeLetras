package proyecto.sopadeletras;

import java.util.*;
/**
 * CLASE MAIN, UNION DE CLASES INGRESO DE VALORES Y CONTROL DE GANAR O PERDER
 * @author OScar Joel Lopez Plata
 * @version 02/12/2021
 */
class Main {
    public static void main(String[] args) {
        //VARIABLES Y ATRIBUTOS INICIALES
        Graficos UI;
        Scanner scn = new Scanner(System.in);
        System.out.println("JUEGO DE SOPA DE LETRAS [V3]");
        int tamañoTablero = 15;
        int nivel = 0;
        int categoria = 0;
        int cantidadPalabras = 0;
        boolean acierto = false;
        int numCorrectas = 0;
        int[] listaCorrectas;
        Reloj reloj;
        float tiempoLim=0;
        float tiempoActual=0;  
        
        //INGRESO DEL NIVEL
        do {
            System.out.println("Ingrese Nivel:\n1) Facil (8 palabras)\n"
                    + "2) Normal (16 palabras)\n3) Dficil (20 palabras)\n"
                    + "4)Muy Dificil(25 Palabras)");
            nivel = scn.nextInt();
        } while (nivel < 1 || nivel > 4);
        if (nivel > 2) {
            tamañoTablero = 20;
        }
        
        //GENERAR TABLERO A PARTIR DE NIVEL
        Tablero tab = new Tablero(tamañoTablero);
        tab.generarTablero();
        
        //ESCOGER CATEGORIA DE PALABRAS
        do {
            System.out.println("Ingrese Categoria:\n1) FRUTAS\n2) VERDURAS\n3) COMPUTADORAS\n"
                    + "4) MUSICA\n5) ANIMALES\n6) PAISES");
            categoria = scn.nextInt();
        } while (categoria < 0 || categoria > 6);
        
        //TIEMPO DE JUEGO E INICIO DE RELOJ
        System.out.println("Ingrese tiempo de juego (Minutos):\n");
        tiempoLim = scn.nextFloat();
        reloj=new Reloj(tiempoLim);
        reloj.setIncio();
        
        //OBTENER PALABRAS Y LLENAR TABLERO 
        ArrayList<String> palabras = tab.getPalabras(nivel, (categoria - 1));
        tab.llenarPalabras();
        cantidadPalabras = palabras.size();
        //INICIAR LISTA DE PALABRAS COMPLETAS
        listaCorrectas = new int[cantidadPalabras];
        for (int i = 0; i < cantidadPalabras; i++) {
            listaCorrectas[i] = 358;
        }
        //INICIAR VENTANA GRAFICA Y GENERAR TABLERO Y LISTA GRAFICA
        UI = new Graficos(tamañoTablero, tab.getTablero(), cantidadPalabras);
        UI.generarLetras();
        UI.generarLista(palabras);
        
        //CICLO DE INGRESO DE COORDENADAS Y PALABRAS
        do {
            boolean errorLectura=false;;
            int x=1;
            int y=1;
            int dir=0;
            String buscar="coco";
            //tab.mostrarPalabras();                //descomentar para ver tablero en consola
            //tab.mostrarTablero();
            
            //TRY-CATCH PARA EVITAR ERRORES DE INGRESO ERRONEO
            try {
                System.out.println("\nIngresa coordenada X [1 - 15]:");       // INGRESO DE X INICIAL
                 x = scn.nextInt();
            } catch (InputMismatchException ex) {
                System.out.println("Debe ingresar obligatoriamente un número entero."); 
                errorLectura=true;
            }
            try {
                System.out.println("Ingresa coordenada Y [1 - 15]:");       //INGRESO DE Y INCIAL
                y = scn.nextInt();
            } catch (InputMismatchException ex) {
                System.out.println("Debe ingresar obligatoriamente un número entero.");
                errorLectura=true;
            }
            try {
                System.out.println("Ingresa palabra a buscar:");            //INGRESO PALABRA A BUSCAR
                 buscar = scn.next();
            } catch (InputMismatchException ex) {
                System.out.println("Debe ingresar obligatoriamente una palabra sin numeros");
                errorLectura=true;
            }
            try {
                System.out.println("Ingresa Direccion [0 - 7]:");           //INGRESO DE DIRRECCION
                System.out.println("DIRECCIONES :\n"
                        + "        [0] ESTE \n"
                        + "        [1] SUR \n"
                        + "        [2] SUR-ESTE \n"
                        + "        [3] NORTE \n"
                        + "        [4] OESTE \n"
                        + "        [5] NOR-OESTE \n"
                        + "        [6] SUR-OESTE \n"
                        + "        [7] NOR-ESTE \n");
               dir = scn.nextInt();
            } catch (InputMismatchException ex) {
                System.out.println("Debe ingresar obligatoriamente un número entero.");
                errorLectura=true;
            }
            
            acierto = tab.buscar((x - 1), (y - 1), dir, buscar);          //MANDAR A BUSCAR AL TABLERO LA PALABRA
             
            if (acierto) {                                                    //SE ENCONTRO, AUMENATAR CONTADOR 
                int index = palabras.indexOf(buscar.toUpperCase().trim());
                listaCorrectas[numCorrectas] = index;
                numCorrectas++;
            }
            System.out.println("Numero de palabra acertadas: " + numCorrectas);
            UI.pintarAciertos(tab.getEncontradas(), listaCorrectas);            //ACTUALIZAR VENTANA CON PALABRAS CORRECTAS
            reloj.tJuego();
            UI.pintarTiempos(reloj.imprimir());
            tiempoActual= (reloj.tJuego()/60);
        } while (numCorrectas < cantidadPalabras && tiempoActual<tiempoLim);
        //FIN DEL JUEGO
        if(tiempoActual>tiempoLim)System.out.println("\n\nSe acabo ele tiempo :c");
        if(numCorrectas>=cantidadPalabras)System.out.println("\nencontraste todas c:");
        System.out.println("FIN DEL JUEGO");
    }
}
