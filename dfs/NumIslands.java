package dfs;

/**
 * LeetCode200.岛屿的个数:
 * https://leetcode-cn.com/problems/number-of-islands/
 * 给定一个由 '1'（陆地）和 '0'（水）组成的的二维网格，计算岛屿的数量。
 * 一个岛被水包围，并且它是通过水平方向或垂直方向上相邻的陆地连接而成的。
 * 你可以假设网格的四个边均被水包围。
 *
 * 示例:
 * 输入:
 * 11110
 * 11010
 * 11000
 * 00000
 * 输出: 1
 *
 * 思路描述
 * 首先需要一个DFS方法，如果该点为1，则向上下左右递归；如果该点为0，则直接返回。
 * 然后需要一个跟grid大小一致的boolean[][] visited二维数组，来标记点(i, j)是否已经访问过。
 * 外层双重循环遍历所有的点，并比较每个点的dfs方法返回值，得到最大值。
 */
public class NumIslands {
    public int numIslands(char[][] grid) {
        if(grid.length == 0) {
            return 0;
        }
        // 访问标记
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int sum = 0;

        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[i].length; j++) {
                sum += dfs(grid, visited, i, j) > 0 ? 1 : 0;
            }
        }
        return sum;
    }

    private int dfs(char[][] grid, boolean[][] visited, int i, int j) {
        // 边界判断
        if(i < 0 || j < 0 || i >= grid.length || j >= grid[0].length) {
            return 0;
        }
        // 重复访问判断
        if(visited[i][j]) {
            return 0;
        }
        // grid[i][j]判断
        if(grid[i][j] == '0') {
            return 0;
        }
        // 标记点(i,j)为访问过
        visited[i][j] = true;
        // 四个方向递归
        return 1 + dfs(grid, visited, i-1, j)
                + dfs(grid, visited, i+1, j)
                + dfs(grid, visited, i, j-1)
                + dfs(grid, visited, i, j+1);
    }
}
