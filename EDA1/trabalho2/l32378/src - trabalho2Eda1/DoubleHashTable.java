/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gilca
 */
public class DoubleHashTable<T> extends HashTable<T>{
	
	private static final int randomSize = 200;
	
	public DoubleHashTable(){
		this(randomSize);
	}
	
	public DoubleHashTable(int n) {
		alocarTabela(n);
		tornarVazia();
	}
        

 

	
	//segunda funcao de hash
	protected int hash2(T s){
		int valHash = 0;
		for( int i = 0; i < ((String) s).length( ); i++ )
			valHash += ((String) s).charAt( i );
		return valHash % table.length;
	}
	
	//metodo que implementa o acesso linear
	protected int procPos(T s){
		int colisoes = 1;
		int posActual = s.hashCode(); // primeira funcao de hash
		int segHash = hash2(s);// segunda funcao de hash
		while(table[posActual] != null && !table[posActual].elem.equals(s))
		{
			posActual += colisoes*segHash; // f(i) = i*hash2(s)
			colisoes += 1;
			if(posActual >= table.length)
				posActual = posActual % table.length;
		}
		return posActual;
	}
}