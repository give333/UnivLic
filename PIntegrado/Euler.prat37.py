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

def retirar_Esquerda(n):
    while len(str(n)) > 1:
        n = int(str(n)[1:])
        if not numero_primo(n):
            return False
    return True

def retirar_Direita(n):
    while len(str(n)) > 1:
        n = int(str(n)[:-1])
        if not numero_primo(n):
            return False
    return True


l = []
n = 11
while len(l) < 11:
    if numero_primo(n) and retirar_Esquerda(n) and retirar_Direita(n):
        l.append(n)
    n += 1
print(l, sum(l))
