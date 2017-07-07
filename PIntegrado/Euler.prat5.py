#i=1
#r=20
#for i in range(n):
   # for i in range(r):
          #if i <= r and i>0:
           #if i%r ==0:
            #    n=n/i
          #  i=i+1
   # else:
      #  break
    
    #r=r-1
#print (n)  






def divisivel ():
    number =1
    storage = False
    for i in range (20,0,-1):
            if number %i ==0:
                if i == 1:
                    storage = True
                else:
                    pass
            else:
                number = number+1
                break
                
    return (number)
