 public class InToPost {
        private Stack<Character> theStack;
        public DoublyLinkedList<String> lista;
        private String input;
        private String output = "";
        public InToPost(String in) {
           input = in;
           theStack = new Stack();
          lista = new DoublyLinkedList();
        }
        public String doTrans() {
            for(int i = 0;i <input.length();i++){
                char ch = input.charAt(i);
                switch (ch){
                    case '+':
                        gotOper(ch,1);
                        break;
                    case '-':
                        gotOper(ch,1);
                        break;
                    case '*':
                        gotOper(ch,2);
                        break;
                    case '/':
                        gotOper(ch,2);
                        break;
                    case '(':
                        theStack.push(ch);
                        break;
                    case ')':
                        gotParen();
                        break;
                    default:
                        output = output + ch;
                        lista.add("" + ch);
                }
            }
            while(!theStack.empty())
                output = output + theStack.pop();
            return output; 
        }
        public void gotOper(char ch, int prec1) {
            while(!theStack.empty()){
                char optop = (char) theStack.pop();
                if(optop == '('){
                    theStack.push(optop);
                    break;
                }else{
                    int percedencia2;
                    if(optop == '+' || optop =='-')
                        percedencia2 =1;
                    else 
                        percedencia2 =2;
                    if (percedencia2 < prec1){
                        theStack.push(optop);
                        break;
                    }else
                        output = output +optop;
                }
            }
            theStack.push(ch);
        }
        public void gotParen(){ 
            while(!theStack.empty()){
                char ch = (char) theStack.pop();
                if(ch == '(') break;
                else output = output + ch;
            }
        }
    }