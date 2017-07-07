/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gilca
 */
public class Elemento<T> {
    boolean del;
    T elem;

    public Elemento(T t) {
        del = false;
        elem = t;
    }

    public void delete() {
        del = true;
    }

    public String toString() {
        return elem.toString();
    }
}