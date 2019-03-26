package dfs;

/**
 * 题目描述:
 * https://leetcode-cn.com/problems/max-area-of-island
 * 给定一个包含了一些0和1的非空二维数组grid，一个岛屿是由四个方向（水平或垂直）的1（代表土地）构成的组合。
 * 你可以假设二维矩阵的四个边缘都被水包围着。
 *
 * 要求:
 * 找到给定的二维数组中最大的岛屿面积。
 * 如果没有岛屿，则返回面积为0。
 *
 * 示例:
 * [[0,0,1,0,0,0,0,1,0,0,0,0,0],
 *  [0,0,0,0,0,0,0,1,1,1,0,0,0],
 *  [0,1,1,0,1,0,0,0,0,0,0,0,0],
 *  [0,1,0,0,1,1,0,0,1,0,1,0,0],
 *  [0,1,0,0,1,1,0,0,1,1,1,0,0],
 *  [0,0,0,0,0,0,0,0,0,0,1,0,0],
 *  [0,0,0,0,0,0,0,1,1,1,0,0,0],
 *  [0,0,0,0,0,0,0,1,1,0,0,0,0]]
 * 对于上面这个给定矩阵应返回6。
 * 注意答案不应该是11，因为岛屿只能包含水平或垂直的四个方向的1。
 *
 * 解题思路:
 * 1. 首先需要一个dfs方法，如果该点为1，则向四个方向递归；如果该点为0，则方法直接返回。该方法需要判断边界情况。
 * 2. 需要一个跟grid大小一样的boolean visited[][]数组来记录该点是否已访问，防止重复访问。
 * 3. 外层双重循环遍历所有的点，并比较每个点的dfs方法返回值，得到最大值。
 */
public class MaxAreaOfIsland {
    public int maxAreaOfIsland(int[][] grid) {
        // 记录该节点是否访问过
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        // 记录最大值
        int max = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                max = Math.max(max, dfs(i, j, grid, visited));
            }
        }
        return max;
    }

    private int dfs(int i, int j, int[][] grid, boolean[][] visited) {
        // 边界判断
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length) {
            return 0;
        }
        // 重复访问判断
        if (visited[i][j]) {
            return 0;
        }
        // 岛屿为0、1判断
        if (grid[i][j] == 0) {
            return 0;
        }
        // 标记该点为已访问
        visited[i][j] = true;
        // 四个方向递归
        return 1 + dfs(i-1, j, grid, visited)
                + dfs(i+1, j, grid, visited)
                + dfs(i, j-1, grid, visited)
                + dfs(i, j+1, grid, visited);
    }
}

/**
 * 解题思路2:
 * 如果原grid数组可以被修改，那么可以不使用辅助空间visited[][]二维数组，直接修改原数组来做标记
 */
class MaxAreaOfIsland2 {
    public int maxAreaOfIsland(int[][] grid) {
        // 记录最大值
        int max = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                max = Math.max(max, dfs(i, j, grid));
            }
        }
        return max;
    }

    public int dfs(int i, int j, int[][] grid) {
        // 边界判断
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length) {
            return 0;
        }

        // 岛屿为0、1判断
        if (grid[i][j] == 0) {
            return 0;
        }

        // 标记grid[i][j] = 0
        grid[i][j] = 0;
        // 四个方向递归
        return 1 + dfs(i-1, j, grid)
                + dfs(i+1, j, grid)
                + dfs(i, j-1, grid)
                + dfs(i, j+1, grid);
    }
}