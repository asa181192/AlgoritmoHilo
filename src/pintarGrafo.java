
public class pintarGrafo extends Thread{
//Esta clase pinta el grafo segun se vaya realizando el recorrido de los hilos 
	/*
	 * @Autor Alfredo Santiago
	 */
	private char addy [][] ;
	public pintarGrafo (char ady[][]) {
		addy = new char [ady.length][ady[0].length];
		addy=ady;
	}
	@Override
	public void run () {
		
		while(main.getSalida()==false){
			addy=main.getAdy();
			for(int i=0;i<addy.length;i++){
		for(int j=0;j<addy[0].length;j++){
			System.out.print(" "+addy[i][j]);
		}
		System.out.print("\n");
	}
		System.out.println("********************");
	try {
		Thread.sleep(50);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
	}//fin while 
	}//fin run
}//fin clase
