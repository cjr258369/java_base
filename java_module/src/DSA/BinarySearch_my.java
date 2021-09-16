package DSA;

/**
 * 二分查找，二分搜索
 * 二分查找的重点来源于：搜索区间的开闭
 * 一般情况下，是这样定义区间的：
 * int left = 0;
 * int rignt = nums.length - 1;  或 int right = nums.length;
 * 上面两个 right 的区别是：（分别代入数字）
 * [2,2] 两端都闭，[2,2) 左闭右开
 * 由此 得到的代码主要的区别在于
 * 1. while(left <= right)  和  while(left < right)
 * 2. left = mid + 1,right = mid - 1   对应区间：[mid+1 , right]，[left , mid - 1]
 * 3. left = mid，right = mid - 1 或 left = mid + 1,right = mid，
 * 对应区间分别是：[left , mid),[mid+1 , right)
 */
public class BinarySearch_my {
    public static void main(String[] args) {
        int[] nums1 = new int[]{1,2,2,2,2,3,4,5,6,7,8};
        int[] nums2 = new int[]{1,2,3,4,5,6,7,8};
        System.out.println(binarySearchRightIndex(nums1,2));
    }

    /**
     * 最基本的普通二分：（所有元素均不重复，整个数组单调递增，找其中的一个元素）
     * 因为初始化 right = nums.length - 1;
     * 所以决定了我们的搜索区间是：[left , right]
     * 所以也决定了：while( left <= right)
     * 同时也决定了：left = mid + 1 和 right = mid - 1
     *
     * 因为只需找一个 target 的索引
     * 所以当 nums[mid] == target 时返回即可。
     * 如果while 循环结束也没找到，直接返回 - 1 即可。
     * （这种写法如果写成 while (left < right) 那么返回就需要判断：nums[left] == target ? left : -1 ）
     *
     * @param nums  数组
     * @param target    目标值
     * @return  返回目标值的索引，没找到返回 -1
     */
    public static int binarySearchNormal(int[] nums , int target){
        int left = 0;
        int right = nums.length - 1;
        while(left<=right){
            int mid = left + ((right - left)>>1);
            if(nums[mid] == target){
                return mid;
            }else if(nums[mid] > target){
                right = mid - 1;
            }else if(nums[mid] < target){
                left = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 寻找左侧边界的二分：
     * 如果我们初始化 right = nums.length
     * 那么就决定了搜索区间：[left , right)
     * 也决定了： while(left < right)
     * 也决定了： left = mid + 1 和 right = mid
     *
     * 如果为了统一写法，把：right = nums.length - 1;
     * 那么就决定了搜索区间：[left , right]
     * 也决定了：while(left <= right)
     * 也决定了：left = mid + 1 和 right = mid - 1;
     * 由于是寻找边界，可能会越界，所以返回结果时，需要判断：
     * if(left >= nums.length || nums[left] != target) return -1
     *
     * 因为需要寻找 target 最左侧的索引
     * 所以当 nums[mid] == target 时，不要返回，而是收紧右侧边界，以及锁定左边界。
     */
    public static int binarySearchLeftIndex(int[] nums , int target){
        int left = 0;
        int right = nums.length - 1;
        while(left<=right){
            int mid = left + ((right - left)>>1);
            if(nums[mid] == target){
                //别返回，锁定左侧边界
                right = mid - 1;
            }else if(nums[mid] > target){
                right = mid - 1;
            }else if(nums[mid] < target){
                left = mid + 1;
            }
        }
        //最后要检查越界情况
        if(left>=nums.length || nums[left]!=target){
            return -1;
        }
        return left;
    }


    /**
     * 寻找左侧边界的二分：
     * 如果我们初始化 right = nums.length
     * 那么就决定了搜索区间：[left , right)
     * 也决定了： while(left < right)
     * 也决定了： left = mid + 1 和 right = mid
     * 同时因为收紧左侧边界时，必须 left = mid + 1
     * 所以最后无论返回 left 还是 right ，必须减一
     *
     * 如果为了统一写法，把：right = nums.length - 1;
     * 那么就决定了搜索区间：[left , right]
     * 也决定了：while(left <= right)
     * 也决定了：left = mid + 1 和 right = mid - 1;
     * 由于是寻找边界，可能会越界，所以返回结果时，需要判断：
     * if(right < 0 || nums[right] != target) return -1
     *
     * 因为需要寻找 target 最右侧的索引
     * 所以当 nums[mid] == target 时，不要返回，而是收紧右侧边界，以及锁定左边界。
     */
    public static int binarySearchRightIndex(int[] nums , int target){
        int left = 0;
        int right = nums.length - 1;
        while(left<=right){
            int mid = left + ((right - left)>>1);
            if(nums[mid] == target){
                //别返回，锁定右侧边界
                left = mid + 1;
            }else if(nums[mid] > target){
                right = mid - 1;
            }else if(nums[mid] < target){
                left = mid + 1;
            }
        }
        //最后要检查越界情况
        if(right < 0 || nums[right] != target){
            return -1;
        }
        return right;
    }


}
