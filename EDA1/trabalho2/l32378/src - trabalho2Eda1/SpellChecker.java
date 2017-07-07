import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;



public class SpellChecker<T> { 


  
    
    static QuadHashTable<String> qt1 = new QuadHashTable<String>(200);
    static ArrayList<String> array1 = new ArrayList<String>();
    static char[] alphabeto={'a','ã','â','á','à','b','c','ç','d','e','ê','é','è','f','g','h','i','í','ì','j','k','l','m','n','o','õ','ô','ó','ò','p','q','r','s','t','u','ú','ù','v','w','x','y','z','A','Ã','Â','Á','À','B','C','Ç','D','E','Ê','É','È','F','G','H','I','Í','Ì','J','K','L','M','N','O','Õ','Ô','Ó','Ò','P','Q','R','S','T','U','Ú','Ù','V','W','X','Y','Z'};
    
   // QuadHashTable<Elemento<String>> qtSujes = new QuadHashTable<Elemento<String>>();
   static HashAberto hashSuj = new HashAberto(200);
 
   
   public SpellChecker(String dicFile, String errorFile, String docFile ) throws IOException {
       
   
        LerFicheiro(dicFile);
         
         spellCheck(docFile);
         
         try{
            FileWriter fw = new FileWriter(errorFile);
            BufferedWriter bw = new BufferedWriter(fw);
                for(int i = 0; i<hashSuj.table.length; i++) {
                    if (hashSuj.table[i] != null){
                         String qualquer = hashSuj.table[i].get(0) + " -> ";
                        for(int j = 1; j<hashSuj.table[i].size(); j++){
                           String cena = qualquer + hashSuj.table[i].get(j);
                           bw.write(cena);
                           bw.newLine();
                        }
                    }
                }
                bw.close();
                fw.close();
         }catch (IOException e){
             e.printStackTrace();
         }
       
   }
    
    

    
    
     public static void main(String[] Args) throws IOException {
         SpellChecker sc = new SpellChecker("dictFile.txt", "errorFile.txt", "docFile.txt");
        
         
      
        
    }
    
    

   
    
    public static void LerFicheiro(String file1)throws IOException {
                try{
                    File file = new File(file1);
                    FileReader fileReader = new FileReader(file);
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    //StringBuffer stringBuffer = new StringBuffer();
                    String line;
                    while((line = bufferedReader.readLine()) != null){
                 
                 
                        String elemento = new String(line);
			qt1.insere(elemento);
                                             
					
                    }
                    fileReader.close();
                    
            }catch (IOException e) {
			e.printStackTrace();
            }
    }
    public static void LerDoc (String file3) throws IOException{
        try{
            
            File file = new File(file3);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            
            while((line = bufferedReader.readLine()) != null){
                 StringTokenizer st = new StringTokenizer(line); 
                 while (st.hasMoreTokens()) {
                     String palavras= (st.nextToken());
                     array1.add(palavras);
                    }
                
                //System.out.println(array1.toString());
                
                             
                }
            
            }catch (IOException e){
                e.printStackTrace();
        }
    }
    public static void  spellCheck(String docFile ) throws IOException{
        LerDoc( docFile);
        for( int i = 0; i<array1.size(); i++){
            if ( qt1.procurar(array1.get(i)) != null){
                System.out.println("SIIIIIM tem  plaavra: " + array1.get(i));
                
            }
            if(qt1.procurar(array1.get(i)) == null){
                System.out.println("Estas não " + array1.get(i));
                if (hashSuj.procurar(array1.get(i)) != null ){
                    System.out.println("T an tabela de erros");
                    
                }else{
                    //gerar
                    ArrayList<String> sugestao = new ArrayList<>();
                    sugestao.add(0, array1.get(i));
                    
                    ArrayList<String> add = Adciona1Letra(array1.get(i));
                    if(!add.isEmpty()){
                        for(int k = 0; k< add.size(); k++){
                            sugestao.add(add.get(k));
                            
                        }
                        //sugestao.add(add);
                    }
                    ArrayList<String> rem = Remove1Letra(array1.get(i));
                    if(!rem.isEmpty()){
                        for(int k = 0; k< rem.size(); k++){
                            sugestao.add(rem.get(k));
                        }
                        //sugestao.add(rem);
                    }
                    ArrayList<String> adj = AlteraAdjacentes(array1.get(i));
                    if(!adj.isEmpty()){
                        for(int k = 0; k< adj.size(); k++){
                            sugestao.add(adj.get(k));
                             //sugestao.add(adj);
                        }
                    }
                       
                    
                    
                    hashSuj.insere(sugestao);
                
                }
                
            }
        }
            
        
    }
     public static String removeCharAt(String s, int pos) {
      return s.substring(0, pos) + s.substring(pos + 1);
   }
 

    
    public static ArrayList<String> Adciona1Letra( String s){
        ArrayList<String> arr = new ArrayList<>();
        for (int i = 0; i< s.length(); i++){
            for(int j = 0; j<alphabeto.length; j++) { //acede chars do alfabeto
                
                String sNew = s.substring(0, i) + alphabeto[j] + s.substring(i);
                if(qt1.procurar(sNew) != null)
                    arr.add(sNew);
            }
        }
        return arr;
    }
    
    public static ArrayList<String> Remove1Letra(String s) {
        ArrayList<String> arr2 = new ArrayList<>();
        for(int i = 0; i < s.length(); i++) {
            String sNew = s.substring(0, i) + s.substring(i+1);
            if(qt1.procurar(sNew) != null)
                arr2.add(sNew);
        }
        return arr2;
    }
    
    public static ArrayList<String> AlteraAdjacentes(String s){
        
        ArrayList<String> arr3 = new ArrayList<>();
        for(int i = 0; i < s.length()-1; i++) {
           String sNew = swap(s, i, i+1);
           if(qt1.procurar(sNew) != null)
               arr3.add(sNew);
        }
        return arr3;
    }
    
    public static String swap(String s, int i, int j) {
        StringBuilder sb = new StringBuilder(s);
        char e = sb.charAt(i);
        char d = sb.charAt(j);
        sb.setCharAt(i, d);
        sb.setCharAt(j, e);
        return sb.toString();
    }
    
}
    

