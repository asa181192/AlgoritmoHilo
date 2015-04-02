
public class hilosLaberinto extends Thread{

	/*
	 * @Autor Alfredo Santiago 
	 */
	//esta clase se encarga de checar algun lugar que no este visitado y que no sea pared dentro del laberinto para crear un nuevo hilo .
	private int x []={0,  0, -1, 1};
	private int y []={1, -1 , 0, 0};
	private int CorX ; //posicion actual en X
	private int CorY;//Posicion actual n Y
	private char adyy [][] ;//
	private boolean visitadoo [][] ;//Matriz de visita 
	private boolean Salida = false ; 
	private int posX ;//posicion adyacente sumada al vector X
	private int posY ;//posicion adyacente sumada al vecot Y
	private	int contador ;//Desicion sobre el movimiento que se hara 
	
	public hilosLaberinto(char [][] ady ,boolean [][] visitado ,int corX,int corY) {
		adyy = new char [ady.length][ady[0].length] ;
		visitadoo = new boolean [visitado.length][visitado[0].length];
		visitadoo=visitado;
		adyy=ady;
		CorX=corX ;
		CorY=corY ;
	
		}
	@Override
	public void run ()  {
		//System.out.println("X"+CorX+ "Y"+CorY);
		while (Salida==false) {
	   //System.out.println("Cor X"+CorX+" CorY "+CorY);
		 contador = 4; 
		for (int i = 0; i < 4; i++) {// NOS PERMITE CHECAR LOS ADYACENTES PARA SABER SI SE ENCUENTRA LA SALIDAY VER CUANTAS PAREDES Y CAMINOS LIBRES HAY .
			
			 posX=x[i]+CorX;
			 posY=y[i]+CorY;
			
			 if(adyy[posX][posY]=='S'){
				 main.setSalida(true);
					System.out.println("salida encontrada en "+posX+"-"+posY);
					System.exit(0);
					}//FIN DE IF 
					
			 
			if(adyy[posX][posY]!='#' && visitadoo[posX][posY]!=true){
					
			contador = contador - 1 ;
			}//FIN DE IF 
			
			
		}//FIN METODO FOR
		if(contador==2 || contador==1 || contador==0){//EN BASE A LO ANTERIOR TOMAMOS UNA DEISICION DE MOVIMIENTO PARA EL HILO 
			
			//System.out.println("BIFURCACION");
			for (int i = 0; i < 4; i++) {
				posX=x[i]+CorX;
				posY=y[i]+CorY;
				if(adyy[posX][posY]!='#' && visitadoo[posX][posY]!=true){
					main.setCordenadaNueva(posX, posY);
					main.setEstadoHiloNuevo(true);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
			Salida=true ; 
		}//FIN DE IF CREADOR DE HILOS 
		
		 if (contador==3) {
			
			for (int i = 0; i < 4; i++) {
				posX=x[i]+CorX;
				posY=y[i]+CorY;
				if(adyy[posX][posY]!='#' && visitadoo[posX][posY]!=true){
					main.SetAdy(CorX,CorY);	
					CorX=posX;
					CorY=posY;
									
					try {
						sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//System.out.println("Mi nueva posicion x"+CorX+ " Y"+ Cor
			}
			}
			visitadoo[CorX][CorY]=true;
		}//FIN DE ELSE 
		 if (contador==4) {//EN CASO DE QUE EL HILO SE ENCUENTRE ATORADO SIN SALIDA AUTOMATICAMENTE MUERE 
			Salida=true;
			
		}
		
		}//fin de while 
		
	}//FIN METODO RUN 
	
	
}
