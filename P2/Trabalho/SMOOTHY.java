package Trabalho;
import java.util.Random;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class SMOOTHY {
		String[][] matriz ;
		String[] cores;
		
		public void construc(int n, int x, int y){
			if (checkfirst(n,x,y)==true){
			
				check(n, x,  y);
				inputs(n);
				
			}}
			
			
		
	        public int resultado(int r){
	        	int i ;
	        	int n=0;
	        	
	        	 for(int linha=1 ; linha <= r ; linha++){
		                for(int coluna = 1; coluna <= r ; coluna ++){
		                	i =0;
		                	while (i<5){
		                	if(matriz[linha][coluna].equals(cores[i])){
		                		n=n+1;
		                	}
		                		
	        	
		                	i++;}} }
	        	int resultado= r*r-n;
	        	System.out.println("Fez uma pontuacao de: " + resultado +  " pontos!!! ");
	        	System.out.println("Parabens ;) ");
	        	return resultado;
	        	 }
				
		public void  criartabuleiro(int S,int n, int c){


	    	
	    	
	    	cores = new String[5];
	    	cores[0] = "Blue";
	    	cores[1] = "Red";
	    	cores[2] = "Green";
	    	cores[3]= "Black";
	    	cores[4]= "Beige";
	    	
	    	matriz = new String [n+2][n+2];
	        
	   
	            Random r = new Random(S);
	            for(int linha=1 ; linha <= n ; linha++){
	                for(int coluna = 1; coluna <= n ; coluna ++){
	                   int a=r.nextInt(c);
	                   matriz[linha][coluna]=cores[a];
	                    
	                }
	            }
	        }
		public boolean verificaprimeiro(int n){
		
	                
	                for(int linha=1 ; linha <= n ; linha++){
	    				
	 		           for(int coluna = 1; coluna <= n ; coluna ++){
	 		        	  if(matriz[linha][coluna] != "vazio" && matriz[linha][coluna]!="    "){

	 		        	   if (matriz[linha][coluna].equals(matriz[linha][coluna+1])){
	 		        		  return true;
	 		        	   } else if (matriz[linha][coluna].equals(matriz[linha][coluna-1])){
		 		        		  return true;
		 		        	} else if (matriz[linha][coluna].equals(matriz[linha+1][coluna])){
		 		        		  return true;
		 		        	} else if (matriz[linha][coluna].equals(matriz[linha-1][coluna])){
		 		        		  return true;
		 		        	}
	 		        	   }     
		                     }
	                    
			
	 		          }  return false;
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
                                    loopcord(n);}

	    	
                                System.out.println("Numero da Coluna:");
                                int y = sc.nextInt();
                                if(y>n || y<=0){System.out.println("Insira uma coluna menor ou igual que " + n + " e maior  ou igual a 1 para jogar sff");
                                loopcord(n);}

	       	
                                if (checkfirst(n,x,y) == true){
                                    construc(n,x,y);
                                }       
                            }
                            catch(InputMismatchException e){
				System.out.println("Por favor, insira numeros");
			//	sc.next();
                            }
                        }
	       	} 
	             
	              
	   public void imprime(int n){
		   System.out.println("\nA O tabuleiro ficou: \n");
                for(int linha=1 ; linha <= n ; linha++){
                    for(int coluna = 1; coluna <= n ; coluna ++){
                        System.out.printf("\t %s \t",matriz[linha][coluna]);
                    }
                    System.out.println();
                }
		   
	   }     
		
		
		public void remover(int n, int x, int y){
			
			matriz[x][y]="    ";
		
		}
		
		public void removenull(int n , int x, int y, String palavra){
                    for(int linha=1 ; linha <= n ; linha++){
                        for(int coluna = 1; coluna <= n ; coluna ++){
	                
	                if (linha+1 <=n) {
	                     if (matriz[linha+1][coluna]!= null ){
	                    if (matriz[linha][coluna].equals("    ") && matriz[linha+1][coluna].equals(palavra)) {
	                            check(n,linha+1,coluna);
	          
	                    }
	                     }
	                }
	               if(coluna+1 <= n) {
	                    if (matriz[linha][coluna+1]!= null ){
	                    if (matriz[linha][coluna].equals("    ") && matriz[linha][coluna+1].equals(palavra)) {
	                            check(n,linha,coluna+1);
	           
	                    }
	                    }
	                }
	                if(linha-1 >=1) {
	                    
	                    
	                    if (matriz[linha-1][coluna]!= null ){
	            	
	                        if (matriz[linha][coluna].equals("    ") && matriz[linha-1][coluna].equals(palavra)) {
	                                check(n,linha-1,coluna);
	               
	                        }
	                    }
	                }
	                if(coluna-1 >= 1) {
	                    if (matriz[linha][coluna-1]!= null ){
	                    
	                        if (matriz[linha][coluna].equals("    ") && matriz[linha][coluna-1].equals(palavra)) {
	                                check(n,linha,coluna-1);
	          
	                        }
	                    }
	                }
			
                        }
                    }
	        }
			
		public boolean verificabaixo(int n){
	
                    String espaco ="    ";
	
			for(int linha=1 ; linha <= n ; linha++){
				
		           for(int coluna = 1; coluna <= n ; coluna ++){
		        	 
		        	   int i=0;
		        	   while (i<5){
                                        if(matriz[linha][coluna].equals(cores[i])){
		        		 
		        		   if (matriz[linha+1][coluna]!=null){
		        			
		        			
		        			   if(matriz[linha+1][coluna].equals(espaco)){
		        				 
		        				   return true;
		        			   }
		        		   }
		        		 
                                        } i++;
                                    }
		           }
	
                    }return false;
                }
		
		
		
		public void baixar(int n){
			String espaco ="    ";
			
			while(verificabaixo(n)){
		
                            for(int linha=1 ; linha <= n ; linha++){
                                for(int coluna = 1; coluna <= n ; coluna ++){
	        	   
                                    if(matriz[linha][coluna].equals(espaco)){
                                        if (matriz[linha-1][coluna]!=null){
                                            matriz[linha][coluna]= matriz[linha-1][coluna];
                                            matriz[linha-1][coluna]="    ";
                                        }
				
                                    }
                                }			
                            }
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
	        	if (verificaprimeiro(n)==true){
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
		                     }} 
	                     else {

		                         System.out.println("Casa nao tem adjacentes, escolha outra casa");
		                         return false;
		                         
		                     }}
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
	                         if(y1 < n+2) {
	                             if(x2 > -1) {
	                                 if(y2 > -1) {

	                                            
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
		 
		 public void loopcord(int tamanho){
			 
			 while ( verificaprimeiro(tamanho)==true){

					System.out.println("Insira novas coordenadas!");
					
					
					
						inputs(tamanho);}
			
		 }
		 
		 public void  jogar(){
			 Scanner sc = new Scanner(System.in);
				int tamanho=0;
				int cores=0;
				int seed = 0;
				
				while(true){
					try{

		    	System.out.println("Tamanho do tabuleiro? (Entre 3-20)");
		    	tamanho = sc.nextInt();

		    	System.out.println("Quantas cores deseja? (Entre 2-5)");
		    	cores = sc.nextInt();
		    	

		    	System.out.println("Seed desejada?");
		    	seed = sc.nextInt();
		    	
		    	
					}catch(InputMismatchException exception){
						sc.next();
						System.out.println("\nIntroduza s� numeros\n");
					}
					
					if (tamanho >= 3 && tamanho <= 20 && cores <=5 && cores >=2){

						break;
					}else{
						System.out.println("Insira valores validos\n");
					}
					
					
		    	
				}
				
				criartabuleiro(seed,tamanho,cores);
				loopcord(tamanho);
				resultado(tamanho);
			 
		 }
		 
		public static void main(String[] args){

		SMOOTHY a = new SMOOTHY();
		a.jogar();

		
                } 
}


