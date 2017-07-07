m=0
for a in range(100):
    for b in range(100):
        total = 0
        for x in str(a**b):
            total+=int(x)
        if total>m:
            m=total
print (m)
