

f1=1 #primeiro fibonacci
f2=1 #segundo fibonacci 
n=3
while n<=100000:
    fib=f1+f2
    if len(str(fib))==1000: 
        break
    f1=f2
    f2=fib
    n=n+1
print('O resultado é: ',n)
