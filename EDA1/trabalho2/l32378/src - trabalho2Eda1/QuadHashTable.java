/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.lang.Math;
/**
 *
 * @author gilca
 */


public class QuadHashTable<T> extends HashTable<T> {

    public QuadHashTable() {
        super();
    }

    public QuadHashTable(int n) {
        super(n);
    }

    public int procPos(T s) {
        int hash = s.hashCode();
        
        hash = hash % table.length;
        hash = Math.abs(hash);
        int counter = 0;
        while(table[hash]!=null){            
            if(table[hash].elem.equals(s))
                return hash++;
           counter++;
           hash = ((hash + (counter * counter)) % table.length);
        }

        return hash;
    }
}
