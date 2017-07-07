

f1=1 #primeiro fibonacci
f2=1 #segundo fibonacci 
n=3
while n <= 100000000:
    fib = f1 + f2 #iterativa
    if len(str(fib))==1000: 
        break
    f1 = f2
    f2 = fib
    n = n+1
    
print('O termo fibonacci com 1000 digitos é o: ',n)
