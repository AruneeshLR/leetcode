class Solution {
    public int maxDistance(List<List<Integer>> arrays) {

        int minValue = arrays.get(0).get(0);
        int maxValue = arrays.get(0).get(arrays.get(0).size() - 1);

        int answer = 0;

        for (int i = 1; i < arrays.size(); i++) {

            int currentMin = arrays.get(i).get(0);
            int currentMax = arrays.get(i).get(arrays.get(i).size() - 1);

            answer = Math.max(answer, currentMax - minValue);
            answer = Math.max(answer, maxValue - currentMin);

            minValue = Math.min(minValue, currentMin);
            maxValue = Math.max(maxValue, currentMax);
        }

        return answer;
    }
}