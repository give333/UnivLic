/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author gilca
 */

 
public class menu {
		String[][] matriz ;
		String[] cores;
		
		public void construc(int n, int x, int y){
			if (checkfirst(n,x,y)==true){
			
				check(n, x,  y);
				inputs(n);
				
			}
                }
                
			
			
		
	        public int resultado(int r){
	        	int i ;
	        	int n=0;
	        	
	        	 for(int linha=1 ; linha <= r ; linha++){
		                for(int coluna = 1; coluna <= r ; coluna ++){
		                	i =0;
		                	while (i<4){
		                	if(matriz[linha][coluna].equals(cores[i])){
		                		n=n+1;
		                	}
		                		
	        	
		                	i++;}} }
	        	int resultado= r*r-n;
	        	System.out.println("Fez uma pontua�ao de:" + resultado +  " Parab�ns ;) ");
	        	return resultado;
	        	 
                }
                
                public void inputs(int n){
			Scanner sc = new Scanner(System.in);
			tospace(n);
			baixar(n);
			verificaColuna(n);
			imprime(n);
			tonull(n);
			if (verificaprimeiro(n)==true){
				try{
					
					

                                    System.out.println("Numero da Linha:");
                                    int x = sc.nextInt();
                                    if(x>n || x<=0){System.out.println("Insira uma linha menor ou igual que " + n + " e maior  ou igual a 1 para jogar sff");
                                    inputs(n);}


                                    System.out.println("Numero da Coluna:");
                                    int y = sc.nextInt();
                                    if(y>n || y<=0){System.out.println("Insira uma coluna menor ou igual que " + n + " e maior  ou igual a 1 para jogar sff");
                                    inputs(n);}


                                    if (checkfirst(n,x,y) == true){
                                            construc(n,x,y);
                                    }
                
                                
                                }catch(InputMismatchException e){
                                    System.out.println("Por favor insira numeros");
			//	sc.next();
                                }
                        }
	       	
		} 
                 public void imprime(int n){
		   System.out.println("\nA Matriz ficou: \n");
                    for(int linha=1 ; linha <= n ; linha++){
                        for(int coluna = 1; coluna <= n ; coluna ++){
                            System.out.printf("\t %s \t",matriz[linha][coluna]);
                        }
                        System.out.println();
                    }
		   
                }
                public void tonull(int n){
			String espaco ="    ";
			for(int linha=1 ; linha <= n ; linha++){
                            for(int coluna = 1; coluna <= n ; coluna ++){
                               if(matriz[linha][coluna].equals(espaco)){
                                       matriz[linha][coluna] = "vazio";

                               }
                            }
		        
                        }
                }
		public void tospace(int n){
			String espaco ="    ";
			for(int linha=1 ; linha <= n ; linha++){
                            for(int coluna = 1; coluna <= n ; coluna ++){
                               if(matriz[linha][coluna].equals("vazio")){
                                       matriz[linha][coluna] = espaco ;

                               }
                            }
		        
                        }
                }
                public boolean checkfirst(int n , int x, int y){
	             String palavra = "";
	             
			String palavraesquerda = "";
			String palavrabaixo= "";
			String palavracima= "";
			String palavradireita = "";
	             
	                palavracima= matriz [x-1][y];
	                palavrabaixo = matriz [x+1][y];
	                palavraesquerda = matriz [x][y-1];
	                palavradireita = matriz [x][y+1];
	                
	                
	             
	                 
	              
	                     palavra= matriz [x][y];  // Minha palavra
	                     if(palavra != "vazio" && palavra!="    "){
	                    	 
	                     
		                     if (palavra.equals(palavracima)  || palavra.equals(palavrabaixo) || palavra.equals(palavraesquerda) || palavra.equals(palavradireita)){
		                         return true;
		                     }
                             } 
	                     else {

		                         System.out.println("Casa nao tem adjacentes, escolha outra casa");
		                         return false;
		                         
		                     }
	                     return false;
	        } 
		 public String check(int n,int x, int y){  

	             String palavra = "";

	                     int x1 = x+1;
	                     int y1 = y+1;
	                     int x2 = x-1;
	                     int y2 = y-1;
	                     palavra= matriz [x][y];  // Minha palavra
	                     

	                    remover(n, x, y);
	                    if(x1 < n+2) {
	                        if(y1 < n+2){
	                            if(x2 > -1) {
	                                if(y2 > -1){
                                            removenull(n,x,y,palavra);	              
	                                }
	                            }
                                 }  
	                                
	                    }

	     
		 return palavra;
	         }
	    
	                     
	                
		 public void verificaColuna(int n){
                    String espaco ="    ";
                    for(int linha=1 ; linha <= n ; linha++){				
                        for(int coluna = 1; coluna <= n ; coluna ++){
                        
                      
                            for (int i = 1; i <=n; i++){
                                if(matriz[n][i-1]!= null){
                                     if (matriz[n][i].equals("    ")){
                                    
                                         for( linha =1; linha <= n; linha++){
                                              matriz[linha][i]= matriz[linha][i-1];
                                              matriz[linha][i-1] = espaco;
                                                 if(matriz[linha][i-1]== null){
                                                     break;
                                                 }
                                         }
                                     }
                                }
                                
                             }
                      
                            
                        }
                    }
            
                }
	
		 
		 
                 
                 
                 
                 
                public static void main(String[] args){
                    Scanner sc = new Scanner(System.in);
			int tamanho=0;
			int cores=0;
			int Seed = 0;
			
			while(true){
				try{

                                    System.out.println("Tamanho da Matriz");
                                    tamanho = sc.nextInt();

                                    System.out.println("Quantas cores deseja? (Entre 2-4)");
                                    cores = sc.nextInt();


                                    System.out.println("Seed desejada?");
                                    Seed = sc.nextInt();
                                    
                                }
                                catch(InputMismatchException exception){
                                    sc.next();
                                    System.out.println("\nIntroduza s� numeros\n");
                                }

                                if (tamanho >= 3 && tamanho <= 15 && cores <=4 && cores >=2 && cores<=tamanho-1){
                                    break;
                                }else{
                                    System.out.println("Insira valores validos\n");
                                }



                         }
	    	
                    SMOOTHY1 a = new SMOOTHY1();

                    a.criartabuleiro(Seed,tamanho,cores);

                    while ( a.verificaprimeiro(tamanho)==true){

                            System.out.println("Insira novas coordenada");
                                    a.inputs(tamanho);
                    }

                    a.resultado(tamanho);
                }
}
