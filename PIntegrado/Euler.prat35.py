import time
start = time.clock()

def numero_primo(n):
    
    if n <= 1:     
        
        return False
    elif n == 2:
        
        return True
    
    elif n % 2 == 0:
        
        return False
    
    else:
        d = 3
        while d * d <= n:
            if n % d == 0:
                return False
            d += 2
        return True
#print(numero_primo(197))   para exemplificar 197
def rotations(n):
	numbers = [n]
	numstring = str(n)
	numstringnew = numstring
	for i in range(len(numstring)-1):
		numstringnew += numstring[i]
	start = 0
	end = len(numstring)
	while end < len(numstringnew):
		numbers.append(int(numstringnew[start+1:end+1]))
		start += 1
		end += 1
	return numbers
#print(rotations(197))  para exemplificar 197
def circular_primes(n):
	counter = 0
	for i in range(n):
		if numero_primo(i):
			#print(i, 'é primo')
			checks = 0
			for j in rotations(i):
				#print(rotations(i), 'rotations')
				if numero_primo(j):
					checks += 1
					#print(checks, 'check')
			if checks == len(rotations(i)):
				counter += 1
	return counter
	
print(circular_primes(1000000))
end = time.clock()
print (end-start)
