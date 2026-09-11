class Solution {
    public int lengthOfLongestSubstring(String s) {
        int n=s.length();
        int maxLen=0;
        for(int i=0;i<n;i++){
            HashSet<Character>set=new HashSet();
            int j=0;
            for(j=i;j<n;j++){
                if(set.contains(s.charAt(j))){
                    break;
                }
                set.add(s.charAt(j));
            }
            int len=j-i;
            if(len>maxLen)maxLen=len;
        }
        return maxLen;
    }
}