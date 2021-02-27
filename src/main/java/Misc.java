import java.util.*;
import java.util.stream.Collectors;

public class Misc {

    public static void main(String[] args) {
        MedianInStream medianInStream = new MedianInStream();
        medianInStream.addNum(1);
        medianInStream.addNum(2);
        medianInStream.addNum(3);
        System.out.println(medianInStream.findMedian());
    }

    void preorder_iterative(TreeNode root){

    }

    void postorder_iterative(TreeNode root){

    }

    void inorder_iterative(TreeNode root){

    }

    public ArrayList<Integer> solve(TreeNode a, int b) {
        Stack<TreeNode> stack = new Stack<>();
        ArrayList<Integer> res = new ArrayList<>();
        stack.push(a);
        while (!stack.isEmpty()){
            TreeNode top = stack.pop();
            res.add(top.val);
            if(top.val == b)
                break;
            if(top.right!=null)
                stack.push(top.right);
            if(top.left!=null)
                stack.push(top.left);
        }
        return res;
    }

    public ArrayList<Integer> solve(TreeNode a) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(a);
        while (!queue.isEmpty()){
            int size = queue.size();
            res.add(queue.peek().val);
            while (size>0) {
                TreeNode node = queue.poll();
                if (node.right != null)
                    queue.offer(node.right);
                if (node.left != null)
                    queue.offer(node.left);
                size--;
            }
        }
        return res;
    }

    TreeNode convert(TreeNode node){
        TreeNode root = node;
        while (root!=null) {
            TreeNode r = root.right;
            TreeNode l = root.left;
            if (r == null && l == null)
                return root;
            TreeNode leftLast = convert(l);
            leftLast.right = r;
            root.right = l;
            root.left = null;
            root = r;
        }
        return node;
    }

    public TreeNode flatten(TreeNode node) {
        if(node == null)
            return null;
        TreeNode leftNode = node.left;
        TreeNode rightNode = node.right;
        if(leftNode!=null){
            node.right = leftNode;
            node.left = null;
            TreeNode rightMost = findRightMost(leftNode);
            rightMost.right = rightNode;
        }
        flatten(node.right);
        return node;
    }

    private TreeNode findRightMost(TreeNode root) {
        if(root.right == null && root.left == null)
            return root;
        return findRightMost(root.right!=null?root.right:root.left);
    }

    public ArrayList<ArrayList<Integer>> verticalOrderTraversal(TreeNode a) {

        Queue<TreeNode> queue = new LinkedList<>();
        Queue<Integer> distQ = new LinkedList<>();
        Map<Integer,ArrayList<Integer>> map = new HashMap<>();
        queue.offer(a);
        distQ.offer(0);
        while (!queue.isEmpty()){
            int size = queue.size();
            while (size>0) {
                TreeNode node = queue.poll();
                int dist = distQ.poll();
                map.computeIfPresent(dist,(k,v) -> {
                    v.add(node.val);
                    return v;
                });
                map.putIfAbsent(dist,new ArrayList<>(Arrays.asList(node.val)));
                if (node.right != null) {
                    queue.offer(node.right);
                    distQ.offer(dist+1);
                }
                if (node.left != null) {
                    queue.offer(node.left);
                    distQ.offer(dist-1);
                }
                size--;
            }
        }
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        map.entrySet().stream()
                .sorted(Comparator.comparing(Map.Entry::getKey))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,(e1,e2) -> e1,LinkedHashMap::new))
                .forEach((k,v) -> res.add(v));
        return res;
    }

    private void preorder(TreeNode a, Map<Integer, ArrayList<Integer>> map, int ind) {
        map.computeIfPresent(ind,(k,v) -> {
           v.add(a.val);
           return v;
        });
        map.putIfAbsent(ind,new ArrayList<>(Arrays.asList(a.val)));
        preorder(a.left,map, ind-1);
        preorder(a.right,map, ind+1);
    }

    boolean preorder(TreeNode root,int val,ArrayList<Integer> res){
        if(root==null)
            return false;
        res.add(root.val);
        if(root.val == val)
            return true;
        boolean leftFound = preorder(root.left,val,res);
        boolean rightFound = preorder(root.right,val,res);
        if(leftFound || rightFound)
            return true;
        res.remove(res.size()-1);
        return false;
    }

    public ArrayList<ArrayList<Integer>> diagonal(ArrayList<ArrayList<Integer>> a) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        int n = a.size();
        for (int i = 0; i < 2*n-1; i++) {
            res.add(new ArrayList<>());
        }
        for (int i = 0; i <n ; i++) {
            for (int j = 0; j <n ; j++) {
                res.get(i+j).add(a.get(i).get(j));
            }
        }
        return res;
    }


    public int[][] diagonal(int[][] a) {
        int n = a.length;
        int[][] res = new int[2*n -1][];
        for (int i = 0; i <2*n -1 ; i++) {
            res[i] = new int[i+1];
        }
        for (int i = 0; i <n ; i++) {
            for (int j = 0; j <n ; j++) {
                res[i+j][j] = a[i][j];
            }
        }
        return res;
    }
}
