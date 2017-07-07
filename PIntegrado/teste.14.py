import time

def prob14():
    start = time.clock()
    max = 0
    result = 0
    for x in range(1000001):
            c = count_terms(x)
            if c > max:
                    max = c
                    result = x
    end = time.clock()	
    print (result, 'em', round(end-start), 's')
    
def count_terms(n):
	if n <= 0: return 0
	c = 1
	while n > 1:
		n = n % 2 == 0 and n/2 or 3*n + 1
		c += 1
	return c

prob14()
    
