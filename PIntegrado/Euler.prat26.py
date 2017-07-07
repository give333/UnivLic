#n=999
#def unit_fraction():
    #for i in range(n):
        
        
def cycle(p):
	if p % 2 == 0 or p % 5 == 0: return 0
	n = 1
	while n < 2000:
		if pow(10, n, p) == 1:
			return n
		n += 1
	return 'err'
		
max = 0
for n in range(2, 1000):
	c = cycle(n)
	if c > max: 
		max, n1 = c, n

print (n1, max)
           
