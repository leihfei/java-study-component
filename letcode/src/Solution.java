import java.util.HashMap;
import java.util.Map;

class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int c = target - nums[i];
            if (map.containsKey(c) && i != map.get(c)) {
                return new int[]{i, map.get(c)};
            }
            map.put(nums[i], i);
        }
        return null;
    }

    public int reverse(int x) {
        if (Integer.MIN_VALUE > x || x > Integer.MAX_VALUE || x == 0) {
            return 0;
        }
        int res = x % x;
        while (x != 0) {
            res *= 10;
            if (Integer.MIN_VALUE /10  > res  || res  > Integer.MAX_VALUE / 10) {
                return 0;
            }
            res = res + x % 10;
            x /= 10;
        }
        return res;
    }

    public static void main(String[] args) {

        System.out.println(new Solution().reverse(1534236469));
    }
}