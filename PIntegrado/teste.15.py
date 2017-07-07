l=[]
for i in range(21):
       l.append([])
       for j in range(21):
              l[i].append(0)
              
for i in range(21):
       l[20][i]=1
       l[i][20]=1

for i in range(19,-1,-1):
       for j in range(19,-1,-1):
              l[i][j]=l[i+1][j]+l[i][j+1]
              
print (l[0][0])
