
def problema_30():
    
    list_all = []

    for i in range(2,355000):
        total = 0
        for a in str(i):
            total = int(a)**5 + total
        if (total == i):
            list_all.append(i)
    print (sum(list_all))
problema_30()
