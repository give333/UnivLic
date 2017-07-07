
#1
def numero_par(n):
    if n%2==0:
        return True
    else:
        return False
#2
def um_algarismo(n):
    if len(str(n))==1:
        return True
    else:
        return False
#3
def portas_logica(x,y,z):
    
    
    if x == ('and'):
        return (y and z)
        
    else:
        return (y or z)
#portas_logica('and' True, True)        
#4
import math 
def numero_meio(x,y,z):
    
    a=max(x,y,z)
    b=min(x,y,z)
    c= (x + y +z)
    d=(a + b)
    print (c-d)
    
#5
def triangulo(x,y,z):
    if (x+y<z) or (y+z<x) or (x+z<y):
        print ("Os valores inseridos não formam um triangulo")
    else:
        if (x==y==z):
            print ("Os valores inseridos", (x,y,z), "formam um triangulo Equilátero")
        elif (x!= y and x!=z and y!=z):
            print ("Os valores inseridos", (x,y,z), "formam um triangulo Escaleno")
        else:
            print ("Os valores inseridos", (x,y,z), "formam um triangulo Isósceles")

            
#6
def ano_bissexto(x):
    if (x%4 ==0) and (x%100 != 0) or (x%400 == 0):
        return (True)

def data_valida(d,m,a):
    y=ano_bissexto(a)
    if y==True:
        
        print ("Data Válida")
    else:
        print ("Data Inválida")

#7
#recursiva                   falta o isinstance
def factorial_r(n):
    if (n == 0):
        return 1
    else:
        return n * factorial_r(n-1)
#iterativa
def factorial_i(n):
    numero = 1
    for i in range(n):
        numero = numero * (i + 1)
    return numero
        
               


#8
#recursiva
def fibonacci(n):
    if n < 2:
        return n
    else:
        return fibonacci(n-1)+fibonacci(n-2)
#iterativa
    
def fib(n):
    a, b = 0, 1
    for i in range(0, n):
        a, b = b, a + b
    return a
    
    
                    
    
    

        
