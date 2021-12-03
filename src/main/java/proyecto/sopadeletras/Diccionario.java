
package proyecto.sopadeletras;
import java.util.*;
/**
 * CLASE DICCIONARIO, COLECCIONES DE PALABRAS Y OBTENCION DE PALABRAS EN JUEGO
 * @author OScar Joel Lopez Plata
 * @version 02/12/2021
 */
public class Diccionario{
//CATEGORIAS DE PALABRAS A JUGAR
  private final String[] frutas={
    "PLATANO","PERA","UVA","MANGO","NARANJA",
    "MANZANA","DURAZNO","SANDIA","GUAYABA","FRESA",
    "CEREZA","PIÑA","LIMON","FRAMBUESA","MORA",
    "MELON","KIWI","PAPAYA","GRANADA","MANDARINA",
    "DATIL","HIGO","MARACUYA","COCO","CIRUELA"
  };

  private final String[] verduras={
    "CEBOLLA","ZANAHORIA","APIO","LECHUGA","RABANO",
    "REPOLLO","TOMATE","PAPA","CAMOTE","AJO",
    "ESPARRAGO","ESPINACA","BROCOLI","COLIFLOR","BERENJENA",
    "CHILE","PEPINO","PIMIENTO","ACELGA","REMOLACHA",
    "COL","ELOTE","GUISANTE","JITOMATE","CALABAZIN"
    };

  private final String[] computadora={
    "GPU","CPU","MONITOR","INTEL","AMD",
    "GABINETE","RAM","BIOS","SATA","USB",
    "MOTHERBOARD","HDD","SSD","ETHERNET","TECLADO",
    "MOUSE","WINDOWS","LINUX","MACOS","NVME",
    "JAVA","PYTHON","SWIFT","PHP","KOTLIN"
    };

    private final String[] musica={
    "PIANO","BATEIRA","GUITARRA","BAJO","FLAUTA",
    "ROCK","POP","INDIE","DISCO","RAP",
    "MELODIA","NOTA","ACORDE","CORO","CANCION",
    "RADIO", "SPOTIFY","ITUNES","UKELELE","TROMPETA",
    "VIOLIN","VOLUMEN","RITMO","JAZZ","SOUNDTRACK"
    };

    private final String[] animales={
    "GATO","PERRO","PEZ","TORTUGA","DELFIN",
    "RATON","LEON","COLIBRI","PERICO","AGUILA",
    "CIMARRON","TIGRE","VACA","CABALLO","POLLO",
    "TIBURON","ERIZO","PATO","CERDO","OVEJA",
    "ABEJA","ZORRO","CONEJO","MAPACHE","SERPIENTE"
    };
    private final String[] paises={
    "MEXICO","ESTADOSUNIDOS","CANADA","CHINA","BRASIL",
    "ESPAÑA","FRANCIA","ITALIA","GRECIA","JAPON",
    "AUSTRALIA","INGLATERRA","INDIA","VENEZUELA","RUSIA",
    "ARGENTINA","PERU","CHILE","ALEMANIA","COLOMBIA",
    "CUBA","EGIPTO","MADAGASCAR","SUECIA","PORTUGAL"
    };

  public Diccionario(){}
 //REGRESAR UNA PALABRAS DE ALGUNA CATEGORIA AL AZAR
  public String darPalabra(int tipo){
    Random rnd= new Random();
    String palabra="";
    int x=rnd.nextInt(25);
    switch(tipo){
      case 0: palabra=frutas[x];
              break;
      case 1: palabra=verduras[x];
              break;
      case 2: palabra=computadora[x];
              break;
      case 3: palabra=musica[x];
              break;
      case 4: palabra=animales[x];
              break;
      case 5: palabra=paises[x];
              break;
      default: System.out.println("Error, tipo no valido");
    }
    return palabra;
  }

  //REGRESAR UN ARRAY DE PALABRAS DE ALGUNA CATEGORIA SIN REPETICION
  public String[] darPalabras(int tipo, int cantidad){
    ArrayList<String> al = new ArrayList<>();
    al.clear();
     int c=0;
    for (int i = 0; i < cantidad; i++) 
    {
      boolean existe=false;
      
      do
      {
       String palabra = darPalabra(tipo);
        if(al.contains(palabra)){
          //System.out.printf("ya existe en al\n");
          existe=true;
          
        }else{
          al.add(palabra);
          //System.out.printf("se añade "+palabra+c+"\n");
          c++;
          existe=false;
        }
          
      }
      while(existe);
      
    }

    String[] palabras= new String[cantidad];
    for(int i=0;i<cantidad;i++){
      palabras[i]=al.get(i);
    }
    
    return palabras;
  }
}