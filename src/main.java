import java.io.ObjectInputStream.GetField;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.text.StyledEditorKit.BoldAction;

/*Programa concurrente , el programa en custion realiza una busqueda en un laberinto , o en un grafo de tamaño 
 * 'N' donde tenemos un nodo inicial y un nodo final , el programa genera en una bifurcacion hasta un maximo 
 * de 4 hilos por un espacio libre , estos hilos asu vez generan 'N' hilos por cada bifurcacion 
 * haciendo un recorrido mas optimo del arbol .
 */
//@autor Alfredo Santiago Alvarado 21/03/2015

/*Actualizacion el algoritmo que diseñe cuenta aun con problemas el problema es el siguiente al cuando la clase de hilos comienza a buscar nuevas salidas 
 * donde se encuentre un espacio que no sea pared y no este visitado en esa parte creara un nuevo hilo y le avisara a la clase main que encontro 
 * un lugar factible para crear el hilo , la clase main toma las nuevas coordenadas de el lugar factible para crearlo y lo crea , sin embargo al crear otro 
 * hilo estos tambien repiten la accion que el pasado dando lugar asi a un porblema de sincronizacion de hilos .
 * 
 * Nota: El programa no esta validado para los limites de un laberinto por lo tanto siempre hay '#' en todo alrededor 
 * Ultima Actualizacion
 * modificada la interaccion entre os hilos........24/03/2015
 * 
 */
public class main {
	static boolean HiloNuevo = false; 
	static private int corX  ;
	static private int corY ;
	static boolean salida = false ;//VARIABLE PARA SALIR DEL LABERINTO EN CUANTO UN HILO ENCUENTRA UNA 'S' SERA TRUE LO QUE NOS SACARA DEL BUCLE 
			static char ady[][] = 
					
		{
				{'#','#','#','#','#','#','#','#'},
				{'#','#','.','.','.','.','.','#'},
				{'#','#','.','#','.','#','.','#'},	
				{'#','#','.','#','.','#','.','#'},
				{'#','#','.','#','#','#','.','#'},
				{'#','#','.','#','.','#','S','#'},
				{'#','#','.','.','.','.','.','#'},
				{'#','#','.','#','#','#','.','#'},
				{'#','#','.','#','.','#','.','#'},
				{'#','#','.','#','.','#','.','#'},
				{'#','#','.','.','.','.','.','#'},
				{'#','#','.','#','.','#','.','#'},
				{'#','#','.','#','.','#','.','#'},
				{'#','.','I','.','.','.','.','#'},
				{'#','#','#','#','#','#','#','#'},
	};	//laberin
	static boolean visitado [][] = new boolean [ady.length][ady[0].length];//LUGARES VISITADOS POR UN HILO
	static char addyGrafopintar [][] =new char [ady.length][ady[0].length];;
	public static void main(String[] args) {
			for (int i = 0; i < ady.length; i++) {
				for (int j = 0; j < ady[0].length; j++) {
						if(ady[i][j]=='I'){
							corX    = i ;
							corY	= j ;
						}
					addyGrafopintar[i][j]=ady[i][j];
				}
			} // SE OBTIENEN COORDENADAS INICIALES DEL LABERINTO 
		visitado[corX][corY]=true;// MARCAMOS COMO VISITADO EL PRIMER PUNTO DONDE NOS ENCONTRAMOS . 
			
			hilosLaberinto miHilo=new hilosLaberinto(ady,visitado,corX,corY);;
			pintarGrafo grafo = new pintarGrafo(addyGrafopintar);
			grafo.start();
			miHilo.start();
			while (getSalida()==false) {//EN CASO DE QUE ALGUN HILO ENCUENTRE LA SALIDA SALIMOS DEL BUCLE 
				if(getEstadoHiloNuevo()==true){
				 new hilosLaberinto(ady,visitado,corX,corY).start();
				 setEstadoHiloNuevo(false);
				}
				
			}
		
	}//FIN DE MAIN 

	
	public static void setEstadoHiloNuevo (boolean estado) {
		HiloNuevo = estado ;
		
	}
	public static boolean getEstadoHiloNuevo () {//ESTE METODO PERMITE AVISAR CUANDO SE DEBE GENERAR UN HILO NUEVO ESTO SERA CUANDO SEA TRUE . 
		return HiloNuevo ;
	}
	
	public static boolean getSalida () {//ESTE METODO PERMITE RETORNAR EL VALOR DE LA SALIDA 
		return salida ;
	}
	public static void setSalida (boolean exit) {
		salida=exit;
	}//ESTE METODO PERMITE SER COMPARTIDO POR CADA UNO DE LOS HILOS GENERADOS PARA QUE CUANDO ALGUNO ALCANCE LA SALIDA LE AVISA ALA CLASE PRINCIPAL . 
	public static void setCordenadaNueva (int CorX,int CorY) {
	corX=CorX;
	corY=CorY;
	addyGrafopintar[CorX][CorY]='0';//MARCAMOS LOS PASOS DE CADA HILO CON UN CERO 
	visitado[corX][corY]=true;
		
	}
	public static char[][] getAdy () {
		return addyGrafopintar;
	}
	public static void SetAdy (int x,int y  ) {
		addyGrafopintar [x][y]='0';
	}
	

}
	