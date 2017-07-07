import time

start=time.clock()

def d(n):
    result = 0
    for i in range(int(n/2), 0, -1):
        if n%i == 0:
            result += i
    return result

res = []

for a in range(1,10000):
    for i in res:
        if i == a:
            break
    else:
        b = d(a)
        if a == d(b) and a != b:
            res.append(a)
            res.append(b)


end=time.clock()
print ('Resultado é: ', (sum(res)), ' e demora ', round(end-start) ,'segundos')
