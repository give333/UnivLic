


def numero_primo(n):
    
    if n <= 1:     
        
        return False
    elif n == 2:
        
        return True
    
    elif n % 2 == 0:
        
        return False
    
    else:
        d = 3
        while d * d <= n:
            if n % d == 0:
                return False
            d += 2
        return True
i = 0
storage = True
primos = []
conta = 0
while storage:
    if numero_primo(i):
        conta += 1
        primos.append(i)
        if conta == 10001:
            break
    i+=1
print (i)



