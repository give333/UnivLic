#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "trie.h"


trie *nova()
{
  return(malloc(sizeof(trie )));
}


void insere(trie *t, char *chave)
{

  int i;

  if (*chave==0) { /* a letra onde aponta já acabou = \0*/ 
    t->existe =1; /* já existe */
  }
  else {
    if (t->prox[*chave - Minel]==NULL) {
      t->prox[*chave - Minel]= malloc(sizeof(trie));
      t->prox[*chave - Minel]->existe=0;
      for (i=0;i<Nel;i++) {
	t->prox[*chave - Minel]->prox[i]=NULL; 
      }
    }
    insere(t->prox[*chave-Minel],chave+1);
  }
}
void lista_ord(trie *t, char *cadeia, int n)
{
  int i;

  if(t->existe){
    *cadeia=0;
     printf("%s\n", cadeia-n );
  }
  for (i=0;i<Nel;i++){
    if (t->prox[i]!=NULL){
      *cadeia=i+Minel;
      lista_ord(t->prox[i], cadeia+1, n+1);
    }
  }
}

void min(trie *t, char *cadeia)
/* Encontra o elemento mínimo da trie, colocando-o na variável cadeia */
{
  int i;

  if(t->existe){
    *cadeia=0;
    
  }
  for (i=0;i<Nel;i++){
    if (t->prox[i]!=NULL){
      *cadeia=i+Minel;
      min(t->prox[i], cadeia+1);
      break;
      
    }
    
  }

} 
void max(trie *t, char *cadeia)
/* Encontra o elemento máximo da trie, colocando-o na variável cadeia */
{
   int j;

  if(t->existe){
    *cadeia=0;
    
  }
  for (j=Nel-1;j>=0;j--){
    if (t->prox[j]!=NULL){
      *cadeia= j+Minel;
      max(t->prox[j], cadeia+1);
      break;
      
    }
    
  }


}
int existe(trie *t, char *cadeia)
{
  int i;

  if (*cadeia == 0){
    return t->existe;
  }
  
  else if(t->prox[*cadeia - Minel] == NULL){
    return 0;
  }
  else{
    return existe(t->prox[*cadeia - Minel], cadeia+1);
  }  
  
} 
void remove_simples(trie *t, char *chave) 
/* remove a string da trie, de forma simples (i.e., muda o existe para 0) */
{
 int i;
 
 if (*chave==0){
    t->existe = 0;
 } 
 else{
    if(t->prox[*chave - Minel]==NULL){
        return;
    }
    else{
      remove_simples(t->prox[*chave - Minel], chave+1);
    }
 }

}
int remove_completo(trie *t, char *chave) //* remove a string da trie, libertando o espaço (free()) dos nós que deixam de fazer falta 
{
  int i;

  if (*chave ==0){
    int i;
    t->existe = 0;
    for (i=0; i<Nel; i++){
      if(t->prox[i]){
        return 0;
      }
    }
  }else{
    if (t->prox[*chave - Minel]==NULL){
          return 0;
        }
        else{
          int j;
          j=remove_completo(t->prox[*chave-Minel], chave+1);
          if (j){
            free(t->prox[*chave-Minel]);
            t->prox[*chave-Minel]=NULL;
            for(j = 0; j < Nel; ++j){
              if(t->prox[j]){
                return 0;
              }
            }
            return(!t->existe);
          }
          else{
            return 0;
          }
        }
      }
}
