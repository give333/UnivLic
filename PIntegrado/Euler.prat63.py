
total = 0
for x in range(1,101) :  #expoent
    for y in range(1,10) : #base
        if len(str(y**x)) == x:
            total += 1
print (total)
