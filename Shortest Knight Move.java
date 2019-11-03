Shortest Knight Move

就是在象棋棋盘上给你两个点A，B，问 个Knight( 哥说，就是骑 那个哈)最少要 步从A跳到B。 珠从来没玩过 国际象棋，于是问Knight咋 。
Turns out只要 任意朝向的L形就好。具体来说，如coordinate是(x,y) 那么在这 的 只knight可以跳到  个position中任何 个
(x-2,y-1); (x-2,y+1);(x+2,y-1);(x+2,y+1);(x-1,y+2);(x-1,y-2);(x+1,y-2);(x+1,y+2).


Solution: BFS

此题不需要建立matrix棋盘矩阵，直接使用坐标BFS即可。

public int shortestKnightMove(int a1, int a2, int b1, int b2) {
	int[][] dirs = new int[][]{{1,2},{2,1},{-1,2},{-2,1},{1,-2},{2,-1},{-1,-2},{-2,-1}};
	Queue<int[]> q = new LinkedList<>();
	boolean[][] visited = new boolean[8][8];
	q.offer(new int[]{a1, a2});
	visited[a1][a2] = true;
	int dist = 0;
	while (!q.isEmpty()) {
		int size = q.size();
		for (int i = 0; i < size; i++) {
			int[] pos = q.poll();
			for (int[] d : dirs) {
				int x = pos[0] + d[0], y = pos[1] + d[1];
				if (x == b1 && y == b2)		return dist + 1;
				if (x < 0 || x >= 8 || y < 0 || y >= 8 || visited[x][y])	continue;
				visited[x][y] = true;
				q.offer(new int[]{x, y});
			}
		}
		dist++;
	}
	return -1;
}

玩法就是马走日
Update: Leetcode 1197. Minimum Knight Moves
https://leetcode.com/problems/minimum-knight-moves/
Infinite board, starting from (0, 0) to input (x, y)
Avoid TLE: decrease the search area to 1/4 of the board each step by checking curr_x * x >= 0 && curr_y * y >= 0
Only one edge case is when (x, y) is (1,1), we can get to (1, 1) by go down-right then jump up-left.

	```
	int minKnightMoves(int x, int y) {
	
		queue<pair<int, int>> q;
		q.push({0, 0});
		vector<int> dx = {1, 2, 2, 1, -1, -2, -2, -1}
		vector<int> dy = {2, 1, -1, -2, 2, 1, -1, -2}
		int count = 0;
		unordered_map<int, unordered_map<int,bool>> visited;
		while (!q.empty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				pair<int, int> front = q.front();
				q.pop();
				if (front.first == x && front.second.y == y) return count;
				for (int j = 0; j < 8; j++) {
					int new_x = dx[i] + front.first;
					int new_y = dy[i] + front.second;
					// count < 3 => handle the edge case
					if (!visited[new_x][new_y] && (count < 3 || (new_x * x >= 0 && new_y * y >= 0))) {
						q.push({new_x, new_y});
						visited[new_x][new_y] = true;
					}
				}
			}
			count++;
		}
		return -1;
	}
	```

	
