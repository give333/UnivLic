import math

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


import math

def gold(n):
    if numero_primo(n):
        return True
    for x in range(1, n):
        if numero_primo(x):
            a = n-x
            a = a/2
            if math.sqrt(a)-int(math.sqrt(a)) == 0:
                return True
    return False

a = 3
while gold(a):
    a += 2

print (a)
