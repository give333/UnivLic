
def Euler_prat9():
    for a in range(1,500):
        for b in range(1,500):
            c = 1000-(a+b)
            if a*a+b*b==c*c:
                return (a*b*c)

Euler_prat9()
