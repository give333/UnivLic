#1
def compara(x,y):
    if x>y:
        print (1)
    elif x==y:
        print (0)
    else:
        print (-1)

#2
import math
def hipotenusa(cateto1, cateto2):
    print ('O valor da hipotenusa é: ', (math.sqrt(round((cateto1**2)+cateto2**2))))

#5
def palindromo(s):
    if str(len(s[0]))==str(len(s[-1])):
        
        
            return True
    return False


#7
    
def print_n(s,n):
    #while n >0:
     #   print(s,n)
     #   n=n-1
    for i in range(n):
        print(s)
    return

def print_ns(s, n):

        if n <= 0:

            return

        print (s)

        print_ns(s, n-1)
