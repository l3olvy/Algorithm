def get_direction_index(direction):
    if direction == 'N':
        return 0
    elif direction == 'E':
        return 1
    elif direction == 'S':
        return 2
    elif direction == 'W':
        return 3

def solution(park, routes):
    park = list(map(lambda x : list(x), park))
    
    w = len(park[0])
    h = len(park)
    
    # 상, 우, 하, 좌
    dx = [-1, 0, 1, 0]
    dy = [0, 1, 0, -1]
    
    x, y = 0, 0
    for i in range(h):
        if 'S' in park[i]:
            x, y = i, park[i].index('S')
            break

    for r in routes:
        con = False
        d, n = r.split()
        i = get_direction_index(d)
        
        for j in range(int(n) + 1):
            nx, ny = x + (dx[i] * j), y + (dy[i] * j)
            if nx < 0 or ny < 0 or nx >= h or ny >= w or park[nx][ny] == 'X':
                con = True
                break

        if con:
            continue

        x, y = nx, ny
    return [x,y]