package DSA.排序;

/**
 * 手撕冒泡排序
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] nums = new int[]{1,3,2,4,7,5,8,6,9};
        //sortArray(nums);
        sortArray2(nums);
        for(int n:nums){
            System.out.print(n);
        }
    }

    //正宗的冒泡排序
    public static int[] sortArray(int[] nums){
        int len = nums.length;
        for(int i=0;i<len;i++){
            for(int j=0;j<len - i - 1;j++){
                if(nums[j]>nums[j+1]){
                    swap(nums,j,j+1);
                }
            }
        }
        return nums;
    }

    //正宗的冒泡排序无法解决，数组有序后，仍需要进行n平方次扫描，因此有如下的优化
    public static int[] sortArray2(int[] nums){
        int len = nums.length;
        //设立标志位
        boolean flag = true;
        //并在for循环的条件增加标志位的判断
        for(int i=0;i<len && flag;i++){
            //如果没发生交换则依旧为false
            flag = false;
            for(int j=0;j<len - i - 1;j++){
                if(nums[j]>nums[j+1]){
                    swap(nums, j, j+1);
                    //发生交换，则修改标志位为true，下次继续判断
                    flag = true;
                }
            }
        }
        return nums;
    }

    public static void swap(int[] nums, int i,int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
