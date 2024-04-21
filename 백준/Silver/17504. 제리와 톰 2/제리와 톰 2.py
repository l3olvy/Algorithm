import sys

n = int(sys.stdin.readline().rstrip())
num = list(map(int, sys.stdin.readline().rstrip().split()))
result = [1] * (n + 1)
result[1] = num[n - 1]

for i in range(2, n + 1):
    result[i] = result[i - 1] * num[n - i] + result[i - 2]

print(result[n] - result[n - 1], result[n])