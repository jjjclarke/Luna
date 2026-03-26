import time

def profiler(n):
    if (n < 2):
        return n
    return profiler(n - 1) + profiler(n - 2)

before = time.time()
print(profiler(35))
after = time.time()
print("time:")
print(after - before)
