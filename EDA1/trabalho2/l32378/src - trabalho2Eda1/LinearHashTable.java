/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.*;
/**
 *
 * @author gilca
 */
public class LinearHashTable<T> extends HashTable<T> {

    
    
    public LinearHashTable(){
        super();
        
    }
    
    public int procPos ( T s){
        
        int hash = s.hashCode();
        hash = hash % table.length;
        hash = Math.abs(hash);
        while(table[hash]!=null){           
                if(table[hash].elem.equals(s))
                    return hash;
                else{
                        hash++;
                        colisoes++;
                        hash = hash % table.length;
                     }
        }
        
    return hash;

    }
}