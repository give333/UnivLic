def prob38 ():
    
    a=[]
    for i in range(1,10000):
        s=""
        for j in range(1,5): #(1,5) por experimentação do exercicio proposto
            if len(s)<9:
               s+=str(i*j)
        if len(s)==9:
            t=list(s)
            t.sort()
            if t==['1','2','3','4','5','6','7','8','9']:
               a.append(s)
    a.sort()
    print (a, 'resultado: ', a[-1])
prob38()
