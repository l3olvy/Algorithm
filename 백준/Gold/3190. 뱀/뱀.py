import sys
from collections import deque

# 보드의 크기 n
n = int(sys.stdin.readline().rstrip())

board = [[0] * n for _ in range(n)]

# 사과의 개수
k = int(sys.stdin.readline().rstrip())

for _ in range(k):
  r, c = map(int, sys.stdin.readline().rstrip().split())
  board[r - 1][c - 1] = 1

# 뱀의 방향 변환 정보
l = int(sys.stdin.readline().rstrip())

change = deque([])

for _ in range(l):
  x, c = sys.stdin.readline().rstrip().split()
  change.append((int(x), c))

# 뱀이 걸치고 있는 칸의 정보를 담는 리스트
snake = deque([(0, 0)])
# snake.append((1, 2))
# print(snake)
# snake.popleft()
# print(snake)

d = 1
dx = [-1, 0, 1, 0]
dy = [0, 1, 0, -1]
# 상, 우, 하, 좌

def game(time):
  global d

  # 시간에 따른 방향 변경
  for c in change:
    if c[0] == time:
      if c[1] == 'L':
        if d > 0:
          d -= 1
        else:
          d = 3
      elif c[1] == 'D':
        if d < 3:
          d += 1
        else:
          d = 0
      change.popleft()
      break

  # 뱀의 머리와 꼬리 위치 파악
  head = snake[len(snake) - 1]
  tail = snake[0]

  # 다음 스탭 위치
  nx, ny = head[0] + dx[d], head[1] + dy[d]

  # 다음 스탭이 벽에 부딪히면 게임 종료
  if nx < 0 or ny < 0 or nx >= n or ny >= n:
    return time
  # 자기 자신과 부딪히면 게임 종료
  elif (nx, ny) in snake:
    return time
  else:
    snake.append((nx, ny))
    if board[nx][ny] == 0:
      snake.popleft()
    else:
      board[nx][ny] = 0
    return 0
      
    
  

def time():
  time = 0
  while True:
    t = game(time)
    if t != 0:
      print(t + 1)
      break
    time += 1

time()