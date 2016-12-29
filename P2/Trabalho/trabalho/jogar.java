/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho;

import java.util.Random;

/**
 *
 * @author gilca
 */
public class jogar {
    
    

public void  criartabuleiro(int S,int n, int c){


	    	
	    	
	    	cores = new String[4];
	    	cores[0] = "Blue";
	    	cores[1] = "Red";
	    	cores[2] = "Green";
	    	cores[3]= "Black";
	    	
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
		        	   while (i<4){
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
