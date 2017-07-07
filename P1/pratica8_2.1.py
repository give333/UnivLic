def histogram(s):
    h=dict()
    for c in s:
        if c in h:
            h[c]+=1
        else:
            h[c]=1

            
        #print(c, h.get('a',0))
    return h
        

    

def print_hist(s):
    h=dict()
    lista=list(s)
    lista.sort()
    lista1=[]
    
    for c in lista:
        if c in h:
            h[c]+=1
        else:
            h[c]=1
            lista1.append(c)

         
    
    for i in lista1:
        a=h.get(i)
        
        print(i, a)


def reverse_lookup(s,v):
    h=dict()
    lista=[]
    for c in s:
        if c in h:
            h[c]+=1
        else:
            h[c]=1
    for c in h:
        if h[c]==v :
            lista.append(c)
    return lista        
            
    
    
    raise ValueError


def invert_dict(s):
    h=dict()
    
    for c in s:
        if c in h:
            h[c]+=1
        else:
            h[c]=1
            
    inv=dict()
    for key in h:
        val=h[key]
        print("chave",val)
        print("valor",key)
        print(inv.setdefault(val,key))
        if inv.setdefault(val,key)==key:
            print("3")
            inv[val].append(9)
        else:
            
            inv.setdefault(val,key)
    return inv


def tem_duplicado(l):
    
    teste=False
    l.sort()
    for i in range(len(l)):

        if l[i] == l[i-1]:

            teste=True
            
    
    return teste
        

def tem_duplicado1(l):
    
    if len(l) == len(set(l)):
        return False
    else:
        return True
    



def chaves_word():
    fin=open('words.txt')

    fin.readline()

    d=dict()
    d1=dict()

    for line in fin:
        d[line]=len(line)-1
       
    fin.close()
    for x in d:
        
        if d[x] in d1:
            
            d1[d[x]] += 1
        else:
            d1[d[x]]=1
               
    return d1.items()



def mais_frequente(s):
    d=dict()
    lista=[]
    
    for c in s:
        
        if c in d:
            d[c]+=1
        else:
            d[c]=1
     
    x=len(d)
  
    while not x ==0:
        for i in d:
            if d[i]==x:
                lista.append(i)
        x-=1
    
    print(lista)

        
    
    
            
            
        
   
