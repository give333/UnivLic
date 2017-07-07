public class ABPI<E extends Comparable<? super E>> implements ABP<E> {
    public NoABP<E> raiz;
    private DoublyLinkedList<E> list;
     
    public ABPI(){
        raiz= null;
    }        
            
    public ABPI(E e){
        raiz = new NoABP<>(e);
        
    }
    public boolean isEmpty(){
        return raiz == null;
    }
    public boolean contains(E x){
        return contains(x, raiz);
    }
    public boolean contains(E x, NoABP<E> c){
        if (c == null)
            return false;
        else if(c.getElement().compareTo(x)==0)
            return true;
        else if(c.getElement().compareTo(x)<0)
            return contains(x, c.getDireito());
        else
            return contains(x, c.getEsquerdo());
            
    }
     public E findMin(){
        if (isEmpty())
            return null;    
        return findMin(raiz);
    }
    public E findMin(NoABP<E> c){
        if (c.getDireito() == null)
            return c.getElement();
        
        else
            return findMin(c.getDireito());
    }
    
    public E findMax(){
        if (isEmpty())
            return null;
        else
            return findMax(raiz);
    }
    public E findMax(NoABP<E> c){
        if (c.getDireito() == null)
            return c.getElement();
        else
            return findMax(c.getDireito());
    }
    
    public void insere(E e){
        raiz = insere(e, raiz);
    }
    
    public NoABP<E> insere(E x, NoABP<E> c){
        if ( c == null ){
            c = new NoABP<>(x);
            }
        else if (c.getElement().compareTo(x)>0){
            c.setEsquerdo(insere(x, c.getEsquerdo()) );
            
        }
        else if (c.getElement().compareTo(x)<0){
            c.setDireito( insere(x, c.getDireito()));
        }
        
        return c;
    
    }
    public void remove (E x){
        raiz = remove(x, raiz);
               
    }
    public NoABP<E> remove (E x, NoABP<E> c){
        if (c == null)
            return c;
        if (c.getElement().compareTo(x)<0)
            c.setDireito(remove(x, c.getDireito()));
        else if (c.getElement().compareTo(x)>0)
            c.setEsquerdo(remove(x, c.getEsquerdo()));
        else if (c.getElement() != null && c.getDireito() != null){
            E min = findMin(c.getDireito());
            c.setElement(min);
            c.setDireito(remove(min, c.getDireito()));
        }
        else if(c.getDireito() == null)
            c = c.getEsquerdo();
        else if (c.getEsquerdo() == null)
            c = c.getDireito();
        else
            c = null;
        
        return c;
        
    }
    public void printEmOrdem(){
        printEmOrdem(raiz);
    }
    
    public void printEmOrdem(NoABP raiz) {
        if(raiz != null) {
            printEmOrdem(raiz.getEsquerdo());
            System.out.print(raiz.getElement());
            printEmOrdem(raiz.getDireito());
        }
    }
    
    public void printPosOrdem(){
        list = new DoublyLinkedList();
        printPosOrdem(raiz);
    }
    
    public void printPosOrdem(NoABP raiz) {
        if(raiz != null) {
            printPosOrdem(raiz.getEsquerdo());
            printPosOrdem(raiz.getDireito());
            list.add((E) raiz.getElement());
        }
    }
    
    public DoublyLinkedList<E> getList() {
        return list;
    }
    
    public void printPreOrdem(){
    }
    
    public int altura() {
        return altura(raiz);
    }
    
    public int altura(NoABP raiz) {
        if(raiz == null) {
            return 0;
        } else {
            return 1 + Math.max(altura(raiz.getEsquerdo()), altura(raiz.getDireito()));
        }
    }
    
    
   
   
}
