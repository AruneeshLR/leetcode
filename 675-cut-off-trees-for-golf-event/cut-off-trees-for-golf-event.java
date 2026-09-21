import java.util.*;

class Solution {

    public int cutOffTree(List<List<Integer>> forest) {

        int m = forest.size();
        int n = forest.get(0).size();

        List<int[]> trees = new ArrayList<>();

        // Store all trees: {height, row, column}
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (forest.get(i).get(j) > 1) {
                    trees.add(new int[]{
                        forest.get(i).get(j), i, j
                    });
                }
            }
        }

        // Cut shortest tree first
        trees.sort((a, b) -> Integer.compare(a[0], b[0]));

        int currentRow = 0;
        int currentCol = 0;
        int totalSteps = 0;

        for (int[] tree : trees) {

            int steps = bfs(
                forest,
                currentRow,
                currentCol,
                tree[1],
                tree[2]
            );

            // Tree cannot be reached
            if (steps == -1) {
                return -1;
            }

            totalSteps += steps;

            currentRow = tree[1];
            currentCol = tree[2];
        }

        return totalSteps;
    }

    public int bfs(
        List<List<Integer>> forest,
        int startRow,
        int startCol,
        int targetRow,
        int targetCol
    ) {

        int m = forest.size();
        int n = forest.get(0).size();

        boolean[][] visited = new boolean[m][n];

        Queue<int[]> queue = new LinkedList<>();

        queue.offer(new int[]{startRow, startCol, 0});
        visited[startRow][startCol] = true;

        int[][] directions = {
            {1, 0},
            {-1, 0},
            {0, 1},
            {0, -1}
        };

        while (!queue.isEmpty()) {

            int[] current = queue.poll();

            int row = current[0];
            int col = current[1];
            int steps = current[2];

            if (row == targetRow && col == targetCol) {
                return steps;
            }

            for (int[] dir : directions) {

                int newRow = row + dir[0];
                int newCol = col + dir[1];

                if (newRow >= 0 && newRow < m &&
                    newCol >= 0 && newCol < n &&
                    !visited[newRow][newCol] &&
                    forest.get(newRow).get(newCol) != 0) {

                    visited[newRow][newCol] = true;

                    queue.offer(new int[]{
                        newRow,
                        newCol,
                        steps + 1
                    });
                }
            }
        }

        return -1;
    }
}