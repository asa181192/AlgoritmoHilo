#Algoritmo Hilo
Programa concurrente , el programa en custion realiza una busqueda en un laberinto , o en un grafo de tamaño 
'N' donde tenemos un nodo inicial y un nodo final , el programa genera en una bifurcacion hasta un maximo 
de 4 hilos por un espacio libre , estos hilos asu vez generan 'N' hilos por cada bifurcacion 
haciendo un recorrido mas optimo del arbol .

@autor Alfredo Santiago Alvarado 21/03/2015

Actualizacion el algoritmo que diseñe cuenta aun con problemas el problema es el siguiente al cuando la clase de hilos comienza a buscar nuevas salidas 
 donde se encuentre un espacio que no sea pared y no este visitado en esa parte creara un nuevo hilo y le avisara a la clase main que encontro 
 un lugar factible para crear el hilo , la clase main toma las nuevas coordenadas de el lugar factible para crearlo y lo crea , sin embargo al crear otro 
 hilo estos tambien repiten la accion que el pasado dando lugar asi a un porblema de sincronizacion de hilos .
  
 Nota: El programa no esta validado para los limites de un laberinto por lo tanto siempre hay '#' en todo alrededor 
 --Ultima Actualizacion
 modificada la interaccion entre os hilos........24/03/2015
 
