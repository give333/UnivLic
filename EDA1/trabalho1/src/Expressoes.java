
import java.util.Scanner;
import java.util.StringTokenizer;

public class Expressoes {
    ABPI<String> abpi = new ABPI();
    
    public Expressoes(){
    }
    public String ler_input(){
        Scanner ficheiro = new Scanner(System.in);
        String input = ficheiro.nextLine();
        Boolean intatual = false;
        StringBuilder sb = new StringBuilder();
        
        try{
            StringTokenizer st = new StringTokenizer(input, " ");  
            while (st.hasMoreTokens()){
                String atual = st.nextToken();
                if (isInteger(atual)){
                    intatual = true;
                   sb.append(atual + " ");
                    
                }
                else if (atual.equals("+")){
                    sb.append(atual + " ");
                }
                else if (atual.equals("-")){
                    sb.append(atual +  " ");
                }else if (atual.equals("*")){
                    sb.append(atual + " ");
                }
                else if (atual.equals("/")){
                    sb.append(atual + " ");
                }
                else if (atual.equals("(")){
                    sb.append(atual + " ");
                }
                else if(atual.equals(")")){
                   sb.append(atual + " ");
                }
                else{
                    throw new Exception("Execção");
                }
                    
                
            }
            
        }
        catch(Exception E){
            System.err.println("*** ERRO *** Input incorrecto. Altere o input de modo que seja correcto ");
            System.exit(0);
        }

        return sb.toString();
               
    }
    //ver se o numero é inteiro
    public static boolean isInteger(String s){
        try{
            Integer.parseInt(s);
        }
        catch(NumberFormatException e){
            return false;
        }catch(NullPointerException e){
            return false;
        }
        return true;
    }
    //funcao para inverter a pilha
    public Stack<String> inverteStack(Stack<String> stack){
        Stack<String> invertida = new Stack<>();
        
        while(!stack.empty())
            invertida.push(stack.pop());
        
        return invertida;
    }
    // priioridade  dos  elementos
    public int prioridade(String s){
        if (s.equals("("))
            return 3;
        else if (s.equals("*") || s.equals("/"))
            return 2;
        else
            return 1;
    }
    
    // coloca a string em postFix na arvore
    public void arvorePoxfix(String input, Stack stack){
        ABPI<Character> arvore =  new ABPI();
       
            
        String[] termos= input.split("\\s+");
		for( int p =0; p< termos.length ;p++){
		}
		String ch;
		NoABP<String> n;
		
		for( int i = 0; i <termos.length; i++){
			ch = termos[i];
			if(isInteger(ch)){
				n = new NoABP(ch);
				stack.push(n);
								
			}else {				
				if(ch.equals("*") || ch.equals("+") || ch.equals("/")  || ch.equals("-")){					
					NoABP direito = (NoABP)stack.pop();
					NoABP esquerdo = (NoABP)stack.pop();
					n = new NoABP(ch);
					n.setEsquerdo(esquerdo);				
					n.setDireito(direito); 
					stack.push(n);
				}
                            }
			
                }
                
		abpi.raiz = (NoABP)stack.pop();        
    }
    public static String put_space(String s){
	String output = " ";
	
		for (int i = 0;i<s.length();i++){
			String cha = s.charAt(i)+"";
			if(cha.matches("\\d+")){
				output = output+cha;
				
			}else{
				output = output+" "+cha+" ";
				
			}
		}
	
		return output+" ";
    }
    //calcula o valor da expressao e devolve
     public double evaluateNode(NoABP raiz){
	        if(raiz.getEsquerdo() != null || raiz.getDireito() != null){
	            double valEsq = evaluateNode(raiz.getEsquerdo());
	            double valDir = evaluateNode(raiz.getDireito());
	            double val = 0;
	            switch((String) raiz.getElement()){
	            case "+":
	                val = valEsq + valDir;
	                break;
	            case "-":
	                val = valEsq - valDir;
	                break;
	            case "*":
	                val = valEsq * valDir;
	                break;
	            case "/":
	                val = valEsq / valDir;
	                break;
	            }
	           
	            return val;
	        }
	        else{
	            return Double.parseDouble((String) raiz.getElement());
	        }
	       
    }
    // adciciona a arvore para desenhar
    public void addTree(NoABP<String> noabp, int x, int y, GraphDraw gd, int i, int nivel, int larg) {
        if(noabp != null) {
            gd.addNode(noabp.toString(), x, y);
            int j = gd.nodesSize()-1;
            
            if(i != -1) {
                gd.addEdge(i, j);
            }
            
            int n = (int) Math.pow(2, nivel);
            int dist = larg / (2 * n);
            if(noabp.getEsquerdo() != null)
                addTree(noabp.getEsquerdo(), x-dist, y+50, gd, j, nivel+1, larg);
            
            if(noabp.getDireito() != null)
                addTree(noabp.getDireito(), x+dist, y+50, gd, j, nivel+1, larg);
        }
    }
     //para desenhar a arvore no graphDraw
    public void draw() {
        GraphDraw frame = new GraphDraw("");
        int alt = abpi.altura();
        int dist = 20;
        int nivel = (int) Math.pow(2, alt + 1);
        
        int largura = dist * (nivel + 1);
        int altura = 70 * (alt + 1);
        
        frame.setSize(largura, altura);
        frame.setVisible(true);
        
        addTree(abpi.raiz, (largura / 2), 50, frame, -1, 1, largura);
    }
    
    public static void main(String[] args) {
        System.out.println("Coloque aqui a sua expressão separada por espaços: ");
        Expressoes ex = new  Expressoes();
        
       
        String input = ex.ler_input();
        InToPost itp = new InToPost(input);
        String output =itp.doTrans();
        
        System.out.println("Expressão em PostFix: ");
        String toSplit = "";
        String[] splitArr = output.split("\\s+");
        for(int i = 0; i < splitArr.length; i++) {
            toSplit += splitArr[i] + " ";
        }
        toSplit = toSplit.substring(0, toSplit.length()-1);
        System.out.println(toSplit);
        
        System.out.println("Lista de Operandos: ");
        toSplit = "";
        for(String s : itp.lista) {
            toSplit += s;
        }
        splitArr = toSplit.split("\\s+");
        toSplit = "[";
        for(int i = 0; i < splitArr.length; i++) {
            toSplit += splitArr[i] + ", ";
        }
        toSplit = toSplit.substring(0, toSplit.length()-2) + "]";
        System.out.println(toSplit);
        
        Stack<String> vazia = new Stack();
        
        output = put_space(output);
    
        ex.arvorePoxfix(output, vazia);
        ex.abpi.printPosOrdem();
        System.out.println("A avaliaçao da expressao é : ");
        System.out.println(ex.evaluateNode(ex.abpi.raiz));
        
        System.out.println("Lista da Expressao em PostFix: ");
        String out = "[";
        for(String s : ex.abpi.getList()) {
            out += s + ", ";
        }
        out = out.substring(0, out.length()-2) + "]";
        System.out.println(out);
        System.out.println("");
       
        ex.draw();
        
        
    }
}
