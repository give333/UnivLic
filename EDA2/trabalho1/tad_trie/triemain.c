#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "trie.h"



int  main()
{ trie *t=nova();
  char v[32];
 char comando[32];

while (1) {
    scanf ("%s", comando);
    if (!strcmp (comando, "i")) {
      scanf ("%s", v);
      insere(t, v);
      printf ("inseriu %s\n", v);
    }
    else if (!strcmp (comando, "l")) {
      lista_ord(t,v,0);
    }
    else if (!strcmp (comando, "min")) {
      min(t, v);
      printf("min: %s\n", v);
    }
    else if (!strcmp (comando, "max")) {
      max(t, v);
      printf("max: %s\n", v);
    }  
    else if (!strcmp (comando, "q")) {
      exit (0);
    }
    else if (!strcmp (comando, "r")) {
      scanf ("%s", v);
      remove_simples(t, v);
      printf("removeu: %s\n", v);
      
    }
    else if (!strcmp (comando, "rc")) {
      scanf ("%s", v);
      remove_completo(t, v);
      printf("removeu: %s\n", v);
      
    }
    else if (!strcmp (comando, "e")) {
      scanf("%s", v);
      int a;
      a = existe(t,v);
      if(a==0){
        printf("Não existe %s\n", v);
      }else{
        printf("Existe %s\n", v);
      }
    }
}
}


