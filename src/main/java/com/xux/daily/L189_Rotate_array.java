package main.java.com.xux.daily;

/**
 * leetcode 189、旋转数组
 * @author 徐信
 * @date 2019-11-24
 */
public class L189_Rotate_array {

    /**
     * 解法一：使用另外一个数组，直接把元素移动到指定位置
     * 时间复杂度为O(n) n为数组长度
     * 空间复杂度为O(n) n维数组长度
     */
    public static void rotate1(int[] nums, int k) {
        if (k % nums.length == 0) return;
        k %= nums.length;
        int tem[] = new int[k];
        for (int i = 0; i < k; i++) {
            tem[i] = nums[i];
        }

        for (int i=nums.length-1; i>k; i--) {
            nums[i] = nums[i - k];
        }
        for (int i=0; i<k; i++){
            nums[i] = tem[i];
        }
    }

    /**
     * 解法二：将解法一简化
     *分析：首先排除特殊情况k=0，并将k和数组长度取模运算，用new重新创建一个数组，这里可以用.clone()方法简化，
     * 下面的循环移动数组元素的逻辑，只把移动元素时会被覆盖的元素备份出来，然后在移动完元素后，把备份的元素移动回去。
     * 拷贝数组元素的操作可以从for循环简化为使用System.arraycopy 这样更快。
     * 时间复杂度O(n) n为数组长度
     * 空间复杂度O(n) n为数组长度
     */
    public static void rotate2(int[] nums, int k) {
        // 余数为0 则刚好移动一圈回到原来位置
        if (k % nums.length == 0) return;
        // 减少移动次数，只需移动余数次数
        k %= nums.length;
        int[] tem = nums.clone();
        System.arraycopy(nums, nums.length - k, tem, 0, k);
        System.arraycopy(nums, 0, tem, k, nums.length - k);
        System.arraycopy(tem, 0, nums, 0, nums.length);
    }

    /**
     * 每次把所有元素向右移动1个位置，移动k轮，使用额外的k个变量的空间，这种叫冒泡旋转
     * 时间复杂度O(k*n) -> O(n) k为右移的实际次数(k%n) n为数组长度
     * 空间复杂度O(n) n为数组长度
     */
    public static void rotate3(int[] nums, int k) {
        // 余数为0 则刚好移动一圈回到原来位置
        if (k % nums.length == 0) return;
        // 减少移动次数，只需移动余数次数
        k %= nums.length;
        for (int i = 0; i < k; i++) {
            // 将最后一个值存临时值
            int tem = nums[nums.length - 1];
            // 向右移动一个值
            System.arraycopy(nums, 0, nums, 1, nums.length - 1);
            // 把原数组最后一个赋给第一个值
            nums[0] = tem;
        }
    }

    /**
     * 解法3：不使用额外空间，三次反转代替移动
     * 时间复杂度O(n)
     * 空间复杂度O(n)
     */
    public static void rotate4(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    //反转数组
    public static void reverse(int[] nums, int left, int right) {
        while (left < right) {
            nums[left] = nums[right] ^ nums[left];
            nums[right] = nums[right] ^ nums[left];
            nums[left] = nums[right] ^ nums[left];
            left++;
            right--;
        }
    }

    public static void main(String[] args) {
        int[] nums = {-1,-100,3,99};
        rotate1(nums, 2);
        rotate2(nums, 2);
        rotate3(nums, 2);
        rotate4(nums, 2);
    }
}
