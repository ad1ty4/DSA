import java.util.*;
import java.util.concurrent.Executors;

public class Misc {

    public static void main(String[] args) {
        System.out.println(braces("((a + b))"));
        System.out.println(braces("()"));
        Executors
    }

    public int numDecodings(String a) {
        if(a.isEmpty())
            return 0;
        int[] dp = new int[a.length()];
        if(a.charAt(0) == '0')
            return 0;
        dp[0] = 1;
        for (int i = 1; i < a.length(); i++) {
            if(isValid(a.charAt(i),a.charAt(i-1)))
                dp[i] = 1+ Math.max(dp[i-1],dp[Math.max(i - 2, 0)]);
            else if(a.charAt(i) == '0')
                return 0;
            else
                dp[i] = dp[i-1];
        }
        return dp[a.length()-1];
    }

    private boolean isValid(char unit, char tens) {
        int val = (tens - 'A')*10 + (unit - 'A');
        return val>=1 && val<=26;
    }

    public int snakeLadder(int[][] a, int[][] b) {
        Queue<Integer> queue = new LinkedList<>();
        Map<Integer,Integer> ladder = new HashMap<>();
        Map<Integer,Integer> snake = new HashMap<>();
        for (int[] ints : a) {
            ladder.put(ints[0], ints[1]);
        }
        for (int[] ints : b) {
            snake.put(ints[0], ints[1]);
        }
        boolean[] visited = new boolean[110];
        int dist=0;
        queue.offer(1);
        visited[1] = true;
        while (!queue.isEmpty()){
            int node = queue.poll();
            int size = queue.size();
            while (size>0) {
                if (node == 100)
                    return dist;
                for (int i = 1; i <= 6; i++) {
                    int n = findNode(node + i, snake, ladder);
                    if (!visited[n]) {
                        queue.offer(n);
                        visited[n] = true;
                    }
                }
                size--;
            }
            dist++;
        }
        return -1;
    }

    private int findNode(int n, Map<Integer, Integer> snake, Map<Integer, Integer> ladder) {
        if(snake.containsKey(n))
            return findNode(snake.get(n),snake,ladder);
        if(ladder.containsKey(n))
            return findNode(ladder.get(n),snake,ladder);
        return n;
    }

    public int isValidBST(TreeNode a) {
        return isValidBSTUtil(a,Integer.MAX_VALUE,Integer.MIN_VALUE)?1:0;
    }

    private boolean isValidBSTUtil(TreeNode a, int minValue, int maxValue) {
        if(a==null)
            return true;
        if(a.val<=minValue || a.val>=maxValue)
            return false;
        return isValidBSTUtil(a.left,minValue,a.val) && isValidBSTUtil(a.right,a.val,maxValue);
    }

    public ArrayList<Integer> twoSum(final List<Integer> a, int b) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        Map<Integer,Integer> map = new HashMap<>();
        map.put(a.get(0),0);
        for (int i = 1; i <a.size(); i++) {
            if(map.containsKey(b-a.get(i))){
                res.add(map.get(b-a.get(i)) + 1);
                res.add(i+1);
                return res;
            }else {
                map.putIfAbsent(a.get(i),i);
            }
        }
        return res;
    }

    public static int braces(String a) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i <a.length() ; i++) {
            if(a.charAt(i) == '+' || a.charAt(i) == '-' || a.charAt(i) == '*' || a.charAt(i) == '/' || a.charAt(i) == '('){
                stack.push(a.charAt(i));
            }else if(a.charAt(i) == ')') {
                if (stack.peek() == '+' || stack.peek() == '-' || stack.peek() == '*' || stack.peek() == '/') {
                    stack.pop();
                } else {
                    return 1;
                }
                stack.pop();
            }
        }

        return 0;
    }

    public static int isValid(String a) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < a.length(); i++) {
            switch (a.charAt(i)){
                case '(':
                    stack.push('(');
                    break;
                case '{':
                    stack.push('{');
                    break;
                case '[':
                    stack.push('[');
                    break;
                case ')':
                    if(stack.isEmpty() || stack.peek() != '(')
                        return 0;
                    stack.pop();
                    break;
                case '}':
                    if(stack.isEmpty() || stack.peek() != '{')
                        return 0;
                    stack.pop();
                    break;
                case ']':
                    if(stack.isEmpty() || stack.peek() != '[')
                        return 0;
                    stack.pop();
                    break;
            }
        }
        return 1;
    }

    /**
     *
     * def WidestGap(n, start, finish):
     *     maxgap = 0
     *     i = 1
     *     min_start = 0
     *     while i < len(start):
     *         if finish[min_start] > start[i] and start[i] < start[min_start]:
     *             maxgap = 0
     *             min_start = i
     *         elif finish[min_start] < start[i]:
     *             maxgap = max(maxgap, (start[i] - finish[min_start]) -1)
     *             min_start = i
     *         i +=1
     *     if (n - max(finish)) > maxgap:
     *         maxgap = n - max(finish)
     *     if min(start) -1 > maxgap:
     *         maxgap = min(start) -1
     *     return maxgap
     * @param n
     * @param start
     * @param finish
     * @return
     */

    public static int widestGap(int n, List<Integer> start, List<Integer> finish) {
        int maxGap=0,minStart=0;
        int len = start.size();
        for (int i = 0; i <len ; i++) {
            if(finish.get(minStart) > start.get(i) && start.get(i) < start.get(minStart)){
                maxGap = 0;
                minStart = i;
            } else if (finish.get(minStart) < start.get(i)) {
                maxGap = Math.max(maxGap, (start.get(i) - finish.get(minStart)) - 1);
                minStart = i;
            }
        }
        if((n - finish.get(len - 1)) > maxGap)
            maxGap = n - finish.get(len -1);
        if((start.get(0) - 1 )> maxGap)
            maxGap = start.get(0) - 1;
        return maxGap;
    }

    /**
     *
     *
     *
     * def findMaxProfit(jobs):
     * tabulation = []
     * for i in range(len(jobs)):
     * tabulation.append(jobs[i][2])
     * for i in range(len(jobs)):
     * for j in range(i + 1, len(jobs)):
     * if jobs[i][1] < jobs[j][0]:
     * tabulation[j] = max(tabulation[j], jobs[j][2] + tabulation[i])
     * return tabulation[-1]
     *
     * def flowerBouquets(p, q, s):
     * if len(s) <= 1:
     * return 0
     * jobs = []
     * type1 = “000”
     * for i in range(len(s)):
     * if s[i:i + 3] == type1:
     * jobs.append([i, i + 2, p])
     * else:
     * cur = s[i: i + 2]
     * if cur == “01” or cur == “10”:
     * jobs.append([i, i + 1, q])
     * if not jobs:
     * return 0
     * return findMaxProfit(jobs)
     *
     * @param a
     * @return
     */

    static int countWays(int p, int q, int r, int last)
    {
        if (p < 0 || q < 0 || r < 0)
            return 0;
        if (p == 1 && q == 0 && r == 0 && last == 0)
            return 1;

        // Same case as above for 'q' and 'r'
        if (p == 0 && q == 1 && r == 0 && last == 1)
            return 1;
        if (p == 0 && q == 0 && r == 1 && last == 2)
            return 1;

        if (last == 0)
            return countWays(p - 1, q, r, 1) +
                    countWays(p - 1, q, r, 2);

        if (last == 1)
            return countWays(p, q - 1, r, 0) +
                    countWays(p, q - 1, r, 2);

        if (last == 2)
            return countWays(p, q, r - 1, 0) +
                    countWays(p, q, r - 1, 1);

        return 0;
    }

//    public static int findWays(int n, int w,int p, int ind){
//            findWays(n,w,p,ind+1);
//            findWays(n,w,p,ind+2);
//    }
//
//    public static int flowerBouquets(int p, int q, String s) {
//        // Write your code here
//        if(s.length()<=1)
//            return 0;
//        String t1 = "000";
//        for (int i = 0; i <s.length() ; i++) {
//            if(s.substring(i,i+3).equals(t1)){
//
//            }else {
//                String curr = s.substring(i,i+2);
//                if(curr.equals("01") || curr.equals("10")){
//
//                }
//            }
//
//        }
//        return findProfit()
//
//
//    }

    public static int jump(int[] a) {
        if(a.length<2)
            return 0;
        int range=0,count=0,oldRange=0;
        for (int i = 0; i <=range; i++) {
            if(i==a.length-1)
                return count;
            range = Math.max(range,a[i] + i);
            if(i>oldRange){
                oldRange = range;
                count++;
            }
        }
        return -1;
    }

    public int maxProfitUtil(final int[] a, int s, int e) {
        int[] prefix = new int[e-s+1];
        prefix[0] = a[s];
        for (int i = s+ 1; i <=e ; i++) {
            prefix[i] = Math.min(prefix[i-1],a[i]);
        }
        int res=0;
        for (int i = s; i <=e ; i++) {
            res = Math.max(res, a[i] - prefix[i]);
        }
        return res;
    }

    public int maxProfit(final int[] a) {
        int res=0;
        for (int i = 0; i <a.length ; i++) {
            res = Math.max(res,maxProfitUtil(a,0,i) + maxProfitUtil(a,i+1,a.length-1));
        }
        return res;
    }

}
