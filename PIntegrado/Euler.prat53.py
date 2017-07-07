import time
start = time.clock()

def factorial(x):
    if x == 0:
        return 1
    else:
        return x * factorial(x-1)
def combination(n, r):
    return (factorial(n))/(factorial(r)*factorial(n-r))

count = 0
for i in range(2, 101):
    for j in range(2, i):
        if combination(i, j) > 1000000:
            count+=1
            
print ("Count = " + str(count))
end = time.clock()
print (end - start)
