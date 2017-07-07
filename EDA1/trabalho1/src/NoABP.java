public class NoABP<E> {
    
    private E element;
    private NoABP<E> direito;
    private NoABP<E> esquerdo;
    
    public NoABP(E x){
        element = x;
        direito = null;
        esquerdo = null;
        
    }
     public NoABP() {
        this(null);
    }

    public NoABP(E e, NoABP<E> n) {
        element = e;
        direito = n;
        esquerdo = null;
    }

    public NoABP(E e, NoABP<E> p, NoABP<E> n) {
        element = e;
        direito = n;
        esquerdo = p;
    }
    public void setElement(E e) {
        this.element = e;
    }

    public void setDireito(NoABP<E> n) {
        direito = n;
    }
    public void setEsquerdo(NoABP<E> p) {
        esquerdo = p;
    }

    public NoABP<E> getDireito() {
        return direito;
    }

    public NoABP<E> getEsquerdo() {
        return esquerdo;
    }
    public E getElement(){
        return element;
    }
    
    public String toString() {
        return this.element.toString();
    }
}
