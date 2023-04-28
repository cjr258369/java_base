package Leetcode.editor.cn;

import java.util.*;

/**
 * 2023-02-07 09:04:25
 * [1604] - 警告一小时内使用相同员工卡大于等于三次的人
 * AlertUsingSameKeyCardThreeOrMoreTimesInAOneHourPeriod.md
 */
 
//力扣公司的员工都使用员工卡来开办公室的门。每当一个员工使用一次他的员工卡，安保系统会记录下员工的名字和使用时间。如果一个员工在一小时时间内使用员工卡的次数大于等于三次，这个系统会自动发布一个 警告 。 
// 给你字符串数组 keyName 和 keyTime ，其中 [keyName[i], keyTime[i]] 对应一个人的名字和他在 某一天 内使用员工卡的时间。 
// 使用时间的格式是 24小时制 ，形如 "HH:MM" ，比方说 "23:51" 和 "09:49" 。 
// 请你返回去重后的收到系统警告的员工名字，将它们按 字典序升序 排序后返回。 
// 请注意 "10:00" - "11:00" 视为一个小时时间范围内，而 "23:51" - "00:10" 不被视为一小时内，因为系统记录的是某一天内的使用情况。 
//
// 示例 1： 
//输入：keyName = ["daniel","daniel","daniel","luis","luis","luis","luis"],keyTime = ["10:00","10:40","11:00","09:00","11:00","13:00","15:00"]
//输出：["daniel"]
//解释："daniel" 在一小时内使用了 3 次员工卡（"10:00"，"10:40"，"11:00"）。
//
// 示例 2： 
//输入：keyName = ["alice","alice","alice","bob","bob","bob","bob"], keyTime = ["12:01","12:00","18:00","21:00","21:20","21:30","23:00"]
//输出：["bob"]
//解释："bob" 在一小时内使用了 3 次员工卡（"21:00"，"21:20"，"21:30"）。
//
// 示例 3： 
//输入：keyName = ["john","john","john"], keyTime = ["23:58","23:59","00:01"]
//输出：[]
//
// 示例 4： 
//输入：keyName = ["leslie","leslie","leslie","clare","clare","clare","clare"],keyTime = ["13:00","13:20","14:00","18:00","18:51","19:30","19:49"]
//输出：["clare","leslie"]
//
// 提示： 
// 1 <= keyName.length, keyTime.length <= 10⁵ 
// keyName.length == keyTime.length 
// keyTime 格式为 "HH:MM" 。 
// 保证 [keyName[i], keyTime[i]] 形成的二元对 互不相同 。 
// 1 <= keyName[i].length <= 10 
// keyName[i] 只包含小写英文字母。 

public class AlertUsingSameKeyCardThreeOrMoreTimesInAOneHourPeriod{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        Solution solution = new AlertUsingSameKeyCardThreeOrMoreTimesInAOneHourPeriod().new Solution();
        System.out.println("预期结果：[daniel] , 运行结果：" + solution.alertNames3(new String[]{"daniel","daniel","daniel","luis","luis","luis","luis"}, new String[]{"10:00","10:40","11:00","09:00","11:00","13:00","15:00"}));
        System.out.println("预期结果：[bob] , 运行结果：" + solution.alertNames3(new String[]{"alice","alice","alice","bob","bob","bob","bob"}, new String[]{"12:01","12:00","18:00","21:00","21:20","21:30","23:00"}));
        System.out.println("预期结果：[] , 运行结果：" + solution.alertNames3(new String[]{"john","john","john"}, new String[]{"23:58","23:59","00:01"}));
        System.out.println("预期结果：[clare,leslie] , 运行结果：" + solution.alertNames3(new String[]{"leslie","leslie","leslie","clare","clare","clare","clare"}, new String[]{"13:00","13:20","14:00","18:00","18:51","19:30","19:49"}));
        System.out.println("预期结果：[b] , 运行结果：" + solution.alertNames3(new String[]{"a","a","a","a","a","b","b","b","b","b","b"}, new String[]{"04:48","23:53","06:36","07:45","12:16","00:52","10:59","17:16","00:36","01:26","22:42"}));
        
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //复杂度分析
        // 时间复杂度：O(nlogn)，其中 n 是数组 keyName 和 keyTime 的长度。需要遍历数组 keyName 和 keyTime，得到每个员工的全部使用员工卡的时间，遍历的时间复杂度是 O(n)，存入哈希表的时间复杂度是 O(1)，因此时间复杂度是 O(n)。
        // 然后判断每个员工是否收到系统警告，需要进行排序和遍历的操作，最坏情况下，排序的时间复杂度是 O(nlogn)，遍历的时间复杂度是 O(n)，因此时间复杂度是 O(nlogn)。因此总时间复杂度是 O(nlogn)。
        // 空间复杂度：O(n)，其中 n 是数组 keyName 和 keyTime 的长度。空间复杂度主要取决于哈希表，需要存储所有员工的全部打卡时间。

        public List<String> alertNames(String[] keyName, String[] keyTime) {
            //同时对name 和 time 一起排序
            TreeMap<String, TreeSet<Integer>> map = new TreeMap<>();
            for(int i = 0; i < keyName.length; i++){
                map.computeIfAbsent(keyName[i], k -> new TreeSet<>()).add(Integer.parseInt(keyTime[i].substring(0,2)) * 60 + Integer.parseInt(keyTime[i].substring(3)));
            }
            List<String> ans = new ArrayList<>();
            for(Map.Entry<String, TreeSet<Integer>> entry : map.entrySet()){
                List<Integer> timeList = new ArrayList<>(entry.getValue());
                for(int i = 2; i < timeList.size(); i++){
                    if(timeList.get(i) - timeList.get(i - 2) <= 60){
                        ans.add(entry.getKey());
                        break;
                    }
                }
            }            
            return ans;
        }
        
        public List<String> alertNames2(String[] keyName, String[] keyTime) {
            TreeMap<String, TreeSet<Integer>> map = new TreeMap<>();
            for(int i = 0; i < keyName.length; i++){
                int idx = ((keyTime[i].charAt(0) - '0') * 10 + (keyTime[i].charAt(1) - '0')) * 60 + ((keyTime[i].charAt(3) - '0') * 10 + (keyTime[i].charAt(4) - '0'));
                map.computeIfAbsent(keyName[i], k -> new TreeSet<>()).add(idx);
            }
            List<String> ans = new ArrayList<>();
            for(Map.Entry<String, TreeSet<Integer>> entry : map.entrySet()){
                List<Integer> timeList = new ArrayList<>(entry.getValue());
                for(int i = 2; i < timeList.size(); i++){
                    if(timeList.get(i) - timeList.get(i - 2) <= 60){
                        ans.add(entry.getKey());
                        break;
                    }
                }
            }            
            return ans;
        }
        
        //优化为使用优先队列
        public List<String> alertNames3(String[] keyName, String[] keyTime) {
            TreeMap<String, PriorityQueue<Integer>> map = new TreeMap<>();
            for(int i = 0; i < keyName.length; i++){
                int idx = (keyTime[i].charAt(0) - '0') * 600 + (keyTime[i].charAt(1) - '0') * 60 + (keyTime[i].charAt(3) - '0') * 10 + (keyTime[i].charAt(4) - '0');
                map.computeIfAbsent(keyName[i], k -> new PriorityQueue<>()).offer(idx);
            }
            List<String> ans = new ArrayList<>();
            for(Map.Entry<String, PriorityQueue<Integer>> entry : map.entrySet()){
                PriorityQueue<Integer> pq = entry.getValue();
                while(pq.size() > 2){
                    int t1 = pq.poll();
                    int t2 = pq.peek();
                    if(t2 - t1 <= 60){
                        t2 = pq.poll();
                        int t3 = pq.peek();
                        if(t3 - t1 <= 60){
                            ans.add(entry.getKey());
                            break;
                        }
                        pq.offer(t2);
                    }
                }
            }            
            return ans;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}