def soma_c(a):
    
    l=[]
    soma=0
    for i in range(len(a)):
        soma=soma+a[i]
        
        l.append(soma)

    print(l)

def verifica_ordem(a):

    teste=1
    for i in range(len(a)):
        
        
        if i>0:
            
            if a[i]<a[i-1]:
                
               teste=0
        
            
                
    if teste==1:
        return True
    else:
        return False


def corta(a):

    del a[0]
    del a[len(a)-1]
    

    
    return a

def meio(l1):
    l2=[]

    for i in range(len(l1)):

        if i!=0 and i!=len(l1)-1:

            l2.append(l1[i])

    print(l2)
       

def e_anagrama(a,b):

    l1=list(a)
    l2=list(b)

    
    if len(a)==len(b):

        
        for i in range(len(l1)):
            teste=False
            for j in range(len (l2)):

                if l1[i]==l2[j]:
                    teste=True
                else:
                    if not teste==1:
                        teste =False

        for i in range(len(l2)):
            teste1=False
            for j in range(len (l1)):

                if l2[i]==l1[j]:
                    teste1=True
                else:
                    if not teste==1:
                        teste1 =False

        if teste==teste1:
            return True
        else:
            return False




def conta_elementos(a,b):
    conta=0


    for i in range(len(a)):


        for j in range(len(b)):

            if a[i]==b[j]:
                conta+=1

    print(conta)



def soma_colunas(a,b,c):

    l=[]


    
























    
