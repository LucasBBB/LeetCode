package dfs;

import java.util.*;
/**
 * LeetCode207.课程表:
 * https://leetcode-cn.com/problems/course-schedule/
 * 现在你总共有n门课需要选，记为0到n-1。
 * 在选修某些课程之前需要一些先修课程。 例如，想要学习课程0，你需要先完成课程1，我们用一个匹配来表示他们：[0,1]。
 * 给定课程总量以及它们的先决条件，判断是否可能完成所有课程的学习？
 *
 * 说明:
 * 输入的先决条件是由边缘列表表示的图形，而不是邻接矩阵。
 * 你可以假定输入的先决条件中没有重复的边。
 *
 * 示例:
 * 输入: 2, [[1,0]]
 * 输出: true
 * 解释: 总共有2门课程。学习课程1之前，你需要完成课程0。所以这是可能的。
 *
 * 思路描述:
 * 1. 假设有 5 门课程，匹配关系分别是 [0,1],[1,2],[2,3],[2,4],[1,4],[0,4]，可以用【有向图】表示其对应关系
 * 2. 我们首先要把用【边缘列表】表示的有向图进行转换，需要用一个 int[] 数组来表示一个节点的【入度】，
 *    在上述例子中，入度数组初始值为 [0, 1, 1, 1, 3]；
 *    还需要用一个 Map<Integer, List<Integer>>【节点指向图】来存储每个节点指向的节点，
 *    节点指向图的初始值为 {0=[1, 4], 1=[2, 4], 2=[3, 4], 3=[], 4=[]}。
 * 3. 然后我们遍历入度数组中全部入度为0的节点，把节点放入一个stack中。
 * 4. 每次从 stack 中 pop 出一个元素，然后把从节点指向图中获取其指向的节点并使之入度 -1，循环此过程直至stack为空。
 * 5. 遍历入度数组查看是否有 inDegree > 0 的点，如果有说明不可能完成所有课程的学习，否则为可能（可以使用标志位来减少此次循环）。
 */
public class ClassSchedule {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // ------------------------------ 第一步 ------------------------------
        // 用inDegree数组表示每个节点的入度
        int[] inDegree = new int[numCourses];
        // 用map来表示，每个节点指向的其他节点列表
        Map<Integer, List<Integer>> map = new HashMap<>();

        for (int[] prerequisite : prerequisites) {
            map.computeIfAbsent(prerequisite[0], k -> new ArrayList<>()).add(prerequisite[1]);
            inDegree[prerequisite[1]]++;
        }

        // ------------------------------ 第二步 ------------------------------
        // 使用stack存储所有入度为0的节点
        LinkedList<Integer> stack = new LinkedList<>();
        // 标识位，每向stack中添加一个新节点就进行自增，如果flag == numCourses即为可以学完全部课程
        int flag = 0;
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                // 此处要注意把入度置为-1防止在接下来的循环中重复操作该节点
                inDegree[i] = -1;
                stack.push(i);
                flag++;
            }
        }

        while (!stack.isEmpty()) {
            // 每次pop出一个节点，并减小其指向节点的入度
            List<Integer> list = map.getOrDefault(stack.pop(), Collections.emptyList());
            for (Integer i : list) {
                inDegree[i]--;
                if (inDegree[i] == 0) {
                    stack.push(i);
                    flag++;
                }
            }
        }

        return flag == numCourses;
    }
}
