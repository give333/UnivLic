def factorial(n):
    if n == 0:
        return 1
    else:
        return n * factorial(n-1)

    
def escolhe_n_e_k(n, k):
    nck = factorial(n) / (factorial(k) * factorial(n-k))
    return nck
    
print(escolhe_n_e_k(40,20))  #40C20
