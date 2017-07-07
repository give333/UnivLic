n= 2**1000
def soma_parcelas(n):
    k= len(str(n))
    storage = 0
    n=str(n)
    for i in range(k):
        storage=storage+int(n[i])
    return storage
        
        

soma_parcelas(n)
