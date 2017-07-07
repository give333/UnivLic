def factorial(n):
    if n == 0:
        return 1
    else:
        return n * factorial(n-1)

def factorial_i(number):
    product = 1
    for i in range(number):
        product = product * (i + 1)
    return product
 
