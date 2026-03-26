def fib(n):
    a = 0
    b = 1
    
    if (n < 2):
        return n
    else:
        for i in range(1, n):
            c = a + b
            a = b
            b = c
        return b
    
result = fib(8)
print(result)