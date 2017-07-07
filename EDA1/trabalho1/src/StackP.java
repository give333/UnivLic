public interface StackP<E> {
    
    public void push(E o);
    
    public E top();
    
    public E pop();
    
    public int size();
    
    public boolean empty();
    
    public void replace(E x, E y);
    
    public String toString();
    
    
}
