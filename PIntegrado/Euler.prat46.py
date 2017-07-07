def prob_52():
    for x in range(1,9999999999):
        x1 = sorted(list(str(x)))
        x2 = sorted(list(str(2*x)))
        x3 = sorted(list(str(3*x)))
        x4 = sorted(list(str(4*x)))
        x5 = sorted(list(str(5*x)))
        x6 = sorted(list(str(6*x)))
        if x1 == x2 == x3 == x4 == x5 == x6:
            print(x)
            break
prob_52()
