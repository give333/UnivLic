def histograma(s):
    d = dict()
    for c in s:
        d[c] = d.get(c, 0)+1
    return d
h1 = histograma('aula')


def print_hist(h):
    l = list(h.keys())
    l.sort()
    for e in l:
        print(e, h[e])
#print_hist(h1)


def reverse_lookup(d, v):
    res=[]
    for c in d:
        if d[c]==v:
            res.append(c)
        return res
            

print (reverse_lookup(h1 , 1))
