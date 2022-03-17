package this_is_coding_test.ch05;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class DFS_BFS {

    /**
     * DFS recursive
     */
    public void dfs(int[][] graph, int v, boolean[] visited) {
        // 방문처리 이후 출력
        visited[v] = true;
        System.out.print(v + " ");

        // 현재 노드와 연결된 다른 노드 재귀적 방문
        for (int i : graph[v]) {
            if (!visited[i]) {
                dfs(graph, i, visited);
            }
        }
    }

    /**
     * BFS queue
     */
    public void bfs(int[][] graph, int v, boolean[] visited) {
        Queue<Integer> queue = new LinkedList<>();

        visited[v] = true;
        queue.add(v);

        while (!queue.isEmpty()) {
            v = queue.poll();
            System.out.print(v + " ");

            for (int i : graph[v]) {
                if (!visited[i]) {
                    visited[i] = true;
                    queue.add(i);
                }
            }
        }
    }

    /**
     * 난이도 중하
     * 1 <= N, M, 1_000
     * 제한) 시간: 1초, 메모리: 128MB
     */
    public int 음료수_얼려_먹기(int n, int m, String[] trays) {
        Stack<int[]> stack = new Stack<>();

        char[][] trayArray = Arrays.stream(trays)
                .map(String::toCharArray)
                .toArray(char[][]::new);

        boolean[][] visited = new boolean[n][m];

        int answer = 0;

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (visited[i][j]) {
                    continue;
                }
                if (trayArray[i][j] == '1') {
                    visited[i][j] = true;
                    continue;
                }

                visited[i][j] = true;
                stack.push(new int[]{i, j});

                while (!stack.isEmpty()) {
                    int x = stack.peek()[0];
                    int y = stack.peek()[1];

                    boolean isRangeInN = x < n - 1;
                    boolean isRangeInM = y < m - 1;

                    if (isRangeInN && isRangeInM && !visited[x + 1][y] && !visited[x][y + 1]) {
                        visited[x + 1][y] = true;
                        visited[x][y + 1] = true;
                        if (trayArray[x + 1][y] != '1') {
                            stack.push(new int[]{x + 1, y});
                        }
                        if (trayArray[x][y + 1] != '1') {
                            stack.push(new int[]{x, y + 1});
                        }
                    } else if (isRangeInN && !visited[x + 1][y]) {
                        visited[x + 1][y] = true;
                        if (trayArray[x + 1][y] != '1') {
                            stack.push(new int[]{x + 1, y});
                        }
                    } else if (isRangeInM && !visited[x][y + 1]) {
                        visited[x][y + 1] = true;
                        if (trayArray[x][y + 1] != '1') {
                            stack.push(new int[]{x, y + 1});
                        }
                    } else {
                        stack.pop();
                    }
                }

                ++answer;
            }
        }

        return answer;
    }

    /**
     * DFS 를 사용하여 해결하는 방법
     */
    public int 음료수_얼려_먹기_2(int n, int m, String[] trays) {
        char[][] graph = Arrays.stream(trays)
                .map(String::toCharArray)
                .toArray(char[][]::new);

        int answer = 0;

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (dfs_for_음료수_얼려_먹기(graph, i, j, n, m)) {
                    ++answer;
                }
            }
        }

        return answer;
    }

    public boolean dfs_for_음료수_얼려_먹기(char[][] graph, int x, int y, int n, int m) {
        // 범위 초과시 false
        if (x <= -1 || x >= n || y <= -1 || y >= m) {
            return false;
        }

        // 해당 노드를 방문하지 않았다면
        if (graph[x][y] != '1') {
            // 방문 처리
            graph[x][y] = '1';

            // 상 하 좌 우 위치 재귀적 호출
            dfs_for_음료수_얼려_먹기(graph, x - 1, y, n, m);
            dfs_for_음료수_얼려_먹기(graph, x + 1, y, n, m);
            dfs_for_음료수_얼려_먹기(graph, x, y - 1, n, m);
            dfs_for_음료수_얼려_먹기(graph, x, y + 1, n, m);

            return true;
        }

        return false;
    }
}
