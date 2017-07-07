def prime(n):
    if n < 2:
        return False
    if n < 4:
        return True
    is_prime = all((n%y for y in range(2,int(n**0.5)+1)))
    return is_prime

primes = []
for n in range(0,100000):
    if prime(n):
        primes.append(n)
        
primes = tuple(primes)
max_l = 20
max_p = 2
for i, p in enumerate(primes):
    sum = p
    for j in range(i+1,len(primes)):
        #print("    ",sum)
        if prime(sum):
            last_prime = sum
            idx = j
        sum += primes[j]
        if sum >= 1000000:
            break
    if idx - i > max_l:
        max_l = idx - i
        max_p = last_prime
print(max_p, max_l)
