package dfs;

/**
 * LeetCode329.矩阵中的最长递增路径:
 * https://leetcode-cn.com/problems/longest-increasing-path-in-a-matrix/
 * 给定一个整数矩阵，找出最长递增路径的长度。
 * 对于每个单元格，你可以往上，下，左，右四个方向移动。
 * 你不能在对角线方向上移动或移动到边界外（即不允许环绕）。
 *
 * 示例:
 * 输入: nums =
 * [
 *   [9,9,4],
 *   [6,6,8],
 *   [2,1,1]
 * ]
 * 输出: 4
 * 解释: 最长递增路径为 [1, 2, 6, 9]。
 *
 * 解题思路:
 * 1. 首先需要一个dfs方法。以向左递归为例，如果`matrix[i-1][j]<=matrix[i][j]`则返回0；否则继续向四个方向递归。
 * 2. 然后需要一个跟`matrix`一样大的二维数组`int[][] dp`，记录已经计算出来的每一个点的最长递增路径，防止重复计算。
 * 3. 外层双重循环遍历所有的点，并比较每个点的dfs方法返回值，得到最大值。
 */
public class LongestIncreasingPath {
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix.length == 0) {
            return 0;
        }
        int[][] dp = new int[matrix.length][matrix[0].length];;
        int max = 0;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                max = Math.max(max, dfs(matrix, dp, i, j, Integer.MIN_VALUE));
            }
        }
        return max;
    }

    private int dfs(int[][] matrix, int[][] dp, int i, int j, int preVal) {
        // 边界判断
        if (i < 0 || j < 0 || i > matrix.length - 1 || j > matrix[i].length - 1) {
            return 0;
        }
        // 是否递增判断
        if (matrix[i][j] <= preVal) {
            return 0;
        }
        // 如果该点已经判断过，则直接返回结果
        if (dp[i][j] != 0) {
            return dp[i][j];
        }

        // 递归上下左右四个点的值
        int left = dfs(matrix, dp, i - 1, j, matrix[i][j]);
        int right = dfs(matrix, dp, i + 1, j, matrix[i][j]);
        int up = dfs(matrix, dp, i, j - 1, matrix[i][j]);
        int down = dfs(matrix, dp, i, j + 1, matrix[i][j]);

        // 状态转移方程为dp[i][j] = 1 + Max(left, right, up, down)
        dp[i][j] = 1 + Math.max(Math.max(left, right), Math.max(up, down));
        return dp[i][j];
    }
}