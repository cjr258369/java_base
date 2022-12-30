package Leetcode.editor.cn;

import java.util.PriorityQueue;
import java.util.TreeSet;

/**
 * 2022-12-30 09:07:53
 * [855] - 考场就座
 * ExamRoom.md
 */
public class ExamRooms{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        ExamRoom2 solution = new ExamRooms().new ExamRoom2(10);
        System.out.println("预期结果：0 , 运行结果：" + solution.seat());
        System.out.println("预期结果：9 , 运行结果：" + solution.seat());
        System.out.println("预期结果：4 , 运行结果：" + solution.seat());
        System.out.println("预期结果：2 , 运行结果：" + solution.seat());
        solution.leave(4);
        System.out.println("预期结果：5 , 运行结果：" + solution.seat());
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    //用 TreeSet 直接暴力，【676 ms，能通过】
    class ExamRoom {
        int n;
        TreeSet<Integer> seats = new TreeSet<>();
        
        public ExamRoom(int n) {
            this.n = n;            
        }
        
        public int seat() {
            int seat = 0;
            if(seats.size() > 0){
                Integer prev = null;
                int dist = seats.first();
                for(int seatNum : seats){
                    //计算每一位与前一位的差
                    if(prev != null){
                        int d = (seatNum - prev) / 2;
                        if(d > dist){
                            dist = d;
                            seat = prev + d;
                        }
                    }
                    prev = seatNum;
                }
                
                //单独计算最终位置
                if(n - 1 - seats.last() > dist){
                    seat = n - 1;
                }
            }
            seats.add(seat);
            return seat;
        }
        
        public void leave(int p) {
            seats.remove(p);
        }
    }
    
    //延迟删除 + 有序集合 + 优先队列 【16 ms 效率更优】
    class ExamRoom2 {
        int n;
        TreeSet<Integer> seats = new TreeSet<>();
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> {
            int d1 = (a[1] - a[0]) / 2, d2 = (b[1] - b[0]) / 2;
            //距离最小的排在前面，如果距离相等，则越小的排在越前面
            return d1 == d2 ? a[0] - b[0] : d2 - d1;
        });
        
        public ExamRoom2(int n) {
            this.n = n;            
        }
        
        public int seat() {
            if(seats.isEmpty()){
                seats.add(0);
                return 0;
            }
            
            int left = seats.first(), right = n - 1 - seats.last();
            while(seats.size() >= 2){
                int[] temp = queue.peek();
                //如果当前位置无效了，那么不进入判断（也即延迟删除：当前已入座的座位不包含左端点、右端点、seats里左端点的右端点不等）
                if(seats.contains(temp[0]) && seats.contains(temp[1]) && seats.higher(temp[0]) == temp[1]){
                    int d = (temp[1] - temp[0]) / 2;
                    // 最左或最右的座位更优
                    if(d <= left || d < right){
                        break;
                    }
                    queue.poll();
                    //新的左、右区间
                    queue.offer(new int[]{temp[0], temp[0] + d});
                    queue.offer(new int[]{temp[0] + d, temp[1]});
                    seats.add(temp[0] + d);
                    return temp[0] + d;                    
                }
                // leave 函数中延迟删除的区间在此时删除
                queue.poll();
            }
            // 最右的位置更优
            if(right > left){
                queue.offer(new int[]{seats.last(), n - 1});
                seats.add(n - 1);
                return n - 1;                
            }else{
                queue.offer(new int[]{0, seats.first()});
                seats.add(0);
                return 0;
            }
        }
        
        public void leave(int p) {
            //如果是删除的p不在左右端点，那么需要合并一个新区间
            if(p != seats.first() && p != seats.last()){
                queue.offer(new int[]{seats.lower(p), seats.higher(p)});
            }
            //原区间会在入座了，因为没了 seat ，而执行延迟删除
            seats.remove(p);
        }
    }
    
    /**
     * Your ExamRoom object will be instantiated and called as such:
     * ExamRoom obj = new ExamRoom(n);
     * int param_1 = obj.seat();
     * obj.leave(p);
     */
    //leetcode submit region end(Prohibit modification and deletion)

}