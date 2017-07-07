
import java.util.ArrayList;


public   class HashAberto {
    //Usa-se arraylist para as sugestoes
    ArrayList<String>[] table;
    int ocupados = 0;
    int colisoes = 0;

    public HashAberto() {
    }

    public HashAberto(int n) {
        table = new ArrayList[n];
    }

    public int ocupados() {
        return ocupados;
    }

    public float factorCarga() {
        return ocupados / (float) table.length;
    }

    public int procPos ( String s){
        
        int hash = s.hashCode();
        
        hash = hash % table.length;
        hash = Math.abs(hash);
        
        while(table[hash]!=null){           
                if(table[hash].get(0).equals(s))
                    return hash;
                else{
                        hash++;
                        colisoes++;
                        hash = hash % table.length;
                     }
        }
        
    return hash;

    }

    public void alocarTabela(int dim) {
        table = new ArrayList[dim];
        ocupados = 0;
    }

    public void tornarVazia() {
        alocarTabela(table.length);
    }

    public ArrayList<String> procurar (String t) {
        int possiblePlace = procPos(t);
        if (table[possiblePlace] != null) {
            if (table[possiblePlace].get(0).equals(t))
                return table[possiblePlace];
        }
        return null;

        // for (int i = 0; i < table.length; i++) {
        //     if (table[i] != null) {
        //         if (table[i].elem.equals(t))
        //             return table[i].elem;
        //     }
        // }
        // return null;
    }

    public void remove(String t) {
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                if (table[i].get(0).equals(t)) table[i].clear();
                ocupados--;
            }
        }
    }

    public void insere(ArrayList<String> As) {
        String t = As.get(0);
        
        int insertPos = procPos(t);
        
        if(table[insertPos] == null || table[insertPos].get(0).equals(null) ) {
            

            table[insertPos] = As;
            
            
            
            
            ocupados++;
        }
        if (factorCarga() > 0.5) {
            rehash();
        }
    }

    public void rehash() {
        ArrayList<String> tempTable[] = table;
        alocarTabela(procPrimo(table.length * 2));
        for(int i = 0; i < tempTable.length; i++){
            if (tempTable[i] != null) {
                if (tempTable[i].get(0).equals(null) ) {
                    insere(tempTable[i]);
                }
            }
        }
    }

    public static int procPrimo(int n) {
        boolean found = false;
        while(!found) {
            if (isPrime(n))
                found = true;
            else
                n++;
        }
        return n;
    }

    public int procPrimoAnterior(int n) {
        boolean found = false;
        while(!found) {
            if (isPrime(n))
                found = true;
            else
                n--;
        }
        return n;
    }

    static boolean isPrime(int n) {
        if(n < 2) return false;
        if(n == 2 || n == 3) return true;
        if(n % 2 == 0) return false;
        for(int i = 3; i * i<= n; i += 2) {
            if(n % i == 0)
                return false;
        }
        return true;
    }

    public void print() {
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                if (table[i].get(0).equals(null) ) {
                    System.out.println(table[i]);
                }
            }
        }
    }

    public void debugPrint() {
        for (int i = 0; i < table.length; i++) {
            if(table[i] != null) {
                if (table[i].get(0).equals(null)   ) {
                    System.out.println("[" + table[i] + "] - deleted");
                } else {
                    System.out.println("[" + table[i] + "]");
                }
            } else {
                System.out.println("[ ]");
            }
        }
    }

}
    

