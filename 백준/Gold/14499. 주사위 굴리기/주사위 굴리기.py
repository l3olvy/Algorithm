import sys

n, m, x, y, k = map(int, sys.stdin.readline().rstrip().split())

# 지도
maps = [list(map(int, sys.stdin.readline().rstrip().split())) for _ in range(n)]

# 명령
com = list(map(int, sys.stdin.readline().rstrip().split()))

# 주사위에 적힌 값을 담는 배열
dice = [0] * 6

dx = [0, 0, 0, -1, 1]
dy = [0, 1, -1, 0, 0]

def turn_dice(dir):
  a, b, c, d, e, f = dice[0], dice[1], dice[2], dice[3], dice[4], dice[5]

  # 동쪽
  if dir == 1:

    dice[0], dice[1], dice[2], dice[3], dice[4], dice[5] = d, b, a, f, e, c
  # 서쪽
  elif dir == 2:
    dice[0], dice[1], dice[2], dice[3], dice[4], dice[5] = c, b, f, a, e, d
  # 북쪽
  elif dir == 3:
    dice[0], dice[1], dice[2], dice[3], dice[4], dice[5] = e, a, c, d, f, b
  # 남쪽
  elif dir == 4:
    dice[0], dice[1], dice[2], dice[3], dice[4], dice[5] = b, f, c, d, a, e

for c in com:
  # 다음 위치
  nx, ny = x + dx[c], y + dy[c]

  # 다음 위치가 바깥이라면 명령 무시
  if nx < 0 or ny < 0 or nx >= n or ny >= m:
    continue
  else:
    x, y = nx, ny

  turn_dice(c)

  if maps[x][y] == 0:
    maps[x][y] = dice[5]
  else:
    dice[5] = maps[x][y]
    maps[x][y] = 0
    
  print(dice[0])