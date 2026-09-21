import java.util.*;

class Solution {
    public int[] smallestRange(List<List<Integer>> nums) {

        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> Integer.compare(a[0], b[0])
        );

        int max = Integer.MIN_VALUE;

        // Put first element of every list into heap
        for (int i = 0; i < nums.size(); i++) {
            int value = nums.get(i).get(0);
            pq.offer(new int[]{value, i, 0});
            max = Math.max(max, value);
        }

        int bestLeft = 0;
        int bestRight = Integer.MAX_VALUE;

        while (pq.size() == nums.size()) {

            int[] current = pq.poll();

            int min = current[0];
            int listIndex = current[1];
            int elementIndex = current[2];

            // Check current range
            if (max - min < bestRight - bestLeft) {
                bestLeft = min;
                bestRight = max;
            }

            // Move forward in the list containing minimum
            if (elementIndex + 1 < nums.get(listIndex).size()) {

                int next = nums.get(listIndex).get(elementIndex + 1);

                pq.offer(new int[]{next, listIndex, elementIndex + 1});

                max = Math.max(max, next);

            } else {
                // This list has no more elements
                break;
            }
        }

        return new int[]{bestLeft, bestRight};
    }
}