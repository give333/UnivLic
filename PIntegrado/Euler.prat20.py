n= 100
def factorial(n):
    if n == 0:
        return 1
    else:
        return n * factorial(n-1)
    
def soma_parcelas(n):
    k= len(str(n))
    storage = 0
    n=str(n)
    for i in range(k):
        storage=storage+int(n[i])
    return storage
        
        

def factorial_in_parcelas(n):    
    x=factorial(n)
    y=soma_parcelas(x)
    print(y)
   
