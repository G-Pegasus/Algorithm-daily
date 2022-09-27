package array;

public class LC_27 {

    public static int removeElement(int[] nums, int val) {
        int count = 0;
        for (int num : nums) {
            if (num != val) {
                count++;
            }
        }

        int location = nums.length - 1;
        for (int i = 0; i < count; i++) {
            for (int j = location; j >= 0; j--) {
                if (nums[i] != val) break;
                if (nums[i] == val && nums[j] != val) {
                    nums[i] = nums[j];
                    location = j - 1;
                    break;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(removeElement(new int[]{0,1,2,2,3,0,4,2}, 2));
    }
}
