package Leetcode.editor.cn;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * 2023-02-09 09:37:44
 * [1797] - 设计一个验证系统
 * DesignAuthenticationManager.md
 */
 
//你需要设计一个包含验证码的验证系统。每一次验证中，用户会收到一个新的验证码，这个验证码在 currentTime 时刻之后 timeToLive 秒过期。如果验证码被更新了，那么它会在 currentTime （可能与之前的 currentTime 不同）时刻延长 timeToLive 秒。 
//
// 请你实现 AuthenticationManager 类： 
// AuthenticationManager(int timeToLive) 构造 AuthenticationManager 并设置 timeToLive 参数。 
// generate(string tokenId, int currentTime) 给定 tokenId ，在当前时间 currentTime 生成一个新的验证码。 
// renew(string tokenId, int currentTime) 将给定 tokenId 且 未过期 的验证码在 currentTime 时刻更新。如果给定 tokenId 对应的验证码不存在或已过期，请你忽略该操作，不会有任何更新操作发生。 
// countUnexpiredTokens(int currentTime) 请返回在给定 currentTime 时刻，未过期 的验证码数目。 
//
// 如果一个验证码在时刻 t 过期，且另一个操作恰好在时刻 t 发生（renew 或者 countUnexpiredTokens 操作），过期事件 优先于 其他操作。 
//
// 示例 1： 
//输入：
//["AuthenticationManager", "renew", "generate", "countUnexpiredTokens","generate", "renew", "renew", "countUnexpiredTokens"]
//[[5], ["aaa", 1], ["aaa", 2], [6], ["bbb", 7], ["aaa", 8], ["bbb", 10], [15]]
//输出：
//[null, null, null, 1, null, null, null, 0]
//
//解释：
//AuthenticationManager authenticationManager = new AuthenticationManager(5); // 构造 AuthenticationManager ，设置 timeToLive = 5 秒。
//authenticationManager.renew("aaa", 1); // 时刻 1 时，没有验证码的 tokenId 为 "aaa" ，没有验证码被更新。
//authenticationManager.generate("aaa", 2); // 时刻 2 时，生成一个 tokenId 为 "aaa" 的新验证码。
//authenticationManager.countUnexpiredTokens(6); // 时刻 6 时，只有 tokenId 为 "aaa" 的验证码未过期，所以返回 1 。
//authenticationManager.generate("bbb", 7); // 时刻 7 时，生成一个 tokenId 为 "bbb" 的新验证码。
//authenticationManager.renew("aaa", 8); // tokenId 为 "aaa" 的验证码在时刻 7 过期，且 8 >= 7 ，所以时刻 8 的renew 操作被忽略，没有验证码被更新。
//authenticationManager.renew("bbb", 10); // tokenId 为 "bbb" 的验证码在时刻 10 没有过期，所以 renew 操作会执行，该 token 将在时刻 15 过期。
//authenticationManager.countUnexpiredTokens(15); // tokenId 为 "bbb" 的验证码在时刻 15过期，tokenId 为 "aaa" 的验证码在时刻 7 过期，所有验证码均已过期，所以返回 0 。
//
// 提示： 
// 1 <= timeToLive <= 10⁸ 
// 1 <= currentTime <= 10⁸ 
// 1 <= tokenId.length <= 5 
// tokenId 只包含小写英文字母。 
// 所有 generate 函数的调用都会包含独一无二的 tokenId 值。 
// 所有函数调用中，currentTime 的值 严格递增 。 
// 所有函数的调用次数总共不超过 2000 次。 

public class DesignAuthenticationManager{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        AuthenticationManager3 authenticationManager = new DesignAuthenticationManager().new AuthenticationManager3(5);
        authenticationManager.renew("aaa", 1);
        authenticationManager.generate("aaa", 2);
        System.out.println("预期结果：1 , 运行结果：" + authenticationManager.countUnexpiredTokens(6));
        authenticationManager.generate("bbb", 7);
        authenticationManager.renew("aaa", 8);
        authenticationManager.renew("bbb", 10);
        authenticationManager.countUnexpiredTokens(15);
        System.out.println("预期结果：0 , 运行结果：" + authenticationManager.countUnexpiredTokens(15));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    //自己写的【74 ms , 43.4 MB】
    class AuthenticationManager {
        HashMap<String, Integer> tokenMap;
        HashMap<Integer, HashSet<String>> timeTokens;
        int timeToLive;
    
        public AuthenticationManager(int timeToLive) {
            tokenMap = new HashMap<>();
            timeTokens = new HashMap<>();
            this.timeToLive = timeToLive;
        }
        
        public void generate(String tokenId, int currentTime) {
            int tokenTime = tokenMap.getOrDefault(tokenId, -1);
            tokenMap.put(tokenId, currentTime);
            if(tokenTime != -1){
                timeTokens.get(tokenTime).remove(tokenId);
            }
            timeTokens.computeIfAbsent(currentTime, k -> new HashSet<>()).add(tokenId);
        }
        
        public void renew(String tokenId, int currentTime) {
            int tokenTime = tokenMap.getOrDefault(tokenId, -1);
            if(tokenTime != -1 && tokenTime + timeToLive > currentTime){
                tokenMap.put(tokenId, currentTime);
                timeTokens.get(tokenTime).remove(tokenId);
                timeTokens.computeIfAbsent(currentTime, k -> new HashSet<>()).add(tokenId);                
            }           
        }
        
        public int countUnexpiredTokens(int currentTime) {
            int ans = 0;
            for(Map.Entry<Integer, HashSet<String>> entry : timeTokens.entrySet()){
                ans += (entry.getKey() + timeToLive > currentTime) ? entry.getValue().size() : 0;
            }
            return ans;    
        }
    }
    
    //直接用单个 HashMap 即可 【56 ms , 42.7 MB】
    // 复杂度分析
    // 时间复杂度：构造函数：O(1)，generate：O(1)，renew：O(1)，countUnexpiredTokens：O(n)，其中 n 为 generate 的调用次数。
    // 空间复杂度：O(n)，其中 n 为 generate 的调用次数，map 中有 n 个元素。
    class AuthenticationManager2 {
        HashMap<String, Integer> tokenMap;
        int timeToLive;
    
        public AuthenticationManager2(int timeToLive) {
            tokenMap = new HashMap<>();
            this.timeToLive = timeToLive;
        }
        
        public void generate(String tokenId, int currentTime) {
            tokenMap.put(tokenId, currentTime + timeToLive);
        }
        
        public void renew(String tokenId, int currentTime) {
            if (tokenMap.getOrDefault(tokenId, 0) > currentTime) {
                tokenMap.put(tokenId, currentTime + timeToLive);
            }   
        }
        
        public int countUnexpiredTokens(int currentTime) {
            int ans = 0;
            for(Map.Entry<String, Integer> entry : tokenMap.entrySet()){
                ans += (entry.getValue() > currentTime) ? 1 : 0;
            }
            return ans;    
        }
    }
    
    // HashMap + 双向链表：【30 ms , 42.8 MB】
    // 用一个双向链表保存验证码和过期时间的顺序。链表的节点保存验证码和过期时间信息，并且在一条链表上，节点保存的过期时间是递增的。额外用一个哈希表 map 来保存验证码-节点对，提供根据验证码来快速访问节点的方法。
    // 调用 generate 时，新建节点，将节点插入链表末尾，并插入 map。
    // 调用 renew 时，如果验证码存在并且未过期，根据 map 访问到节点，更新过期时间，并将节点从原来位置移动到链表末尾。
    // 调用 countUnexpiredTokens 时，从链表头部开始，删除过期的节点，并从 map 删除。最后 map 的长度就是未过期的验证码的数量。
    
    // 复杂度分析
    // 时间复杂度：构造函数：O(1)，generate：O(1)，renew：O(1)，所有 countUnexpiredTokens 的调用加起来的复杂度是 O(n)，其中 n 为 generate 的总调用次数。
    // 空间复杂度：O(n)，其中 n 为 generate 的调用次数，map 和链表中有 n 个元素。
    class AuthenticationManager3 {
        int timeToLive;
        HashMap<String, DoublyLinkedListNode> tokenMap;
        DoublyLinkedListNode head, tail;
    
        public AuthenticationManager3(int timeToLive) {
            this.timeToLive = timeToLive;
            tokenMap = new HashMap<>();
            head = new DoublyLinkedListNode(-1);
            tail = new DoublyLinkedListNode(-1);
            head.next = tail;
            tail.prev = head;
        }
        
        public void generate(String tokenId, int currentTime) {
            DoublyLinkedListNode node = new DoublyLinkedListNode(currentTime + timeToLive, tokenId, tail.prev, tail);
            tokenMap.put(tokenId, node);
            tail.prev.next = node;
            tail.prev = node;
        }
        
        public void renew(String tokenId, int currentTime) {
            //if (tokenMap.getOrDefault(tokenId, new DoublyLinkedListNode(0, "xxx")).expire > currentTime) {
            if(tokenMap.containsKey(tokenId) && tokenMap.get(tokenId).expire > currentTime){
                DoublyLinkedListNode node = tokenMap.get(tokenId);
                //更新节点的 过期时间
                node.expire = currentTime + timeToLive;
                //更新节点的前驱节点的 next 指向它的next
                node.prev.next = node.next;
                //更新节点的后驱节点的 prev prev
                node.next.prev = node.prev;
                //更新节点的 next 指向尾部
                node.next = tail;
                //更新节点的 prev 指向尾部的 prev
                node.prev = tail.prev;
                //更新尾部的 prev 的 next 指向 节点
                tail.prev.next = node;
                //更新尾部的前驱节点指向 node
                tail.prev = node;
            }
        }
        
        public int countUnexpiredTokens(int currentTime) {
            while(head.next.expire > 0 && head.next.expire <= currentTime){
                DoublyLinkedListNode node = head.next;
                tokenMap.remove(node.tokenId);
                head.next = node.next;
                node.next.prev = head;                
            }
            return tokenMap.size();    
        }
        
        class DoublyLinkedListNode{
            int expire;
            String tokenId;
            DoublyLinkedListNode prev;
            DoublyLinkedListNode next;

            public DoublyLinkedListNode(int expire) {
                this(expire, null, null, null);
            }
            
            public DoublyLinkedListNode(int expire, String tokenId) {
                this(expire, tokenId, null, null);
            }
            
            public DoublyLinkedListNode(int expire, String tokenId, DoublyLinkedListNode prev, DoublyLinkedListNode next) {
                this.expire = expire;
                this.tokenId = tokenId;
                this.prev = prev;
                this.next = next;
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}