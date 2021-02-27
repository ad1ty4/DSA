import java.util.*;
import java.util.stream.Collectors;

public class Knapsack {

    /**
     *
     * N = 3
     * W = 4
     * values[] = {1,2,3}
     * weight[] = {4,5,1}
     * Output: 3
     *
     */

    public static void main(String[] args) {
        System.out.println(lps("AACECAAAA")[0]);
    }

    public int solve(String a) {
        StringBuilder sb = new StringBuilder(a);
        sb.append("@");
        for (int i = a.length()-1; i >=0 ; i--)
            sb.append(a.charAt(i));
        int[] l = lps(sb.toString());
        return a.length() - l[l.length-1];

    }

    public ListNode addTwoNumbers(ListNode a, ListNode b) {
        ListNode res = new ListNode(-1);
        ListNode p = res;
        int carry=0;
        while (a!=null || b!=null){
            int sum = carry;
            if(a!=null) {
                sum += a.val;
                a = a.next;
            }
            if(b!=null) {
                sum += b.val;
                b = b.next;
            }
            p.next = new ListNode(sum%10);
            p = p.next;
            carry = sum/10;
        }
        if(carry>0)
            p.next = new ListNode(carry);
        return res.next;
    }


    private boolean isEven(String a) {
        return (a.charAt(a.length()-1) - '0')%2 == 0;
    }


    public String addBinary(String a, String b) {
        int n = Math.max(a.trim().length(),b.length());
        int carry = 0;
        a = pad(a,n);
        b = pad(b,n);
        StringBuilder sb = new StringBuilder();
        for (int i = n-1; i >=0 ; i--) {
            int sum = a.charAt(i) -'0' + b.charAt(i) - '0' + carry;
            if(sum==3){
                sb.append("1");
                carry=1;
            }else if(sum==2){
                sb.append("0");
                carry = 1;
            }else if(sum==1){
                sb.append("1");
                carry=0;
            }else {
                sb.append("0");
                carry=0;
            }
        }
        return sb.reverse().toString();
    }

//    public int solve(String a) {
//        int i=0,j=a.length()-1;
//        while (a.charAt(i)){
//            if(a.charAt(i) == a.charAt(j)){
//                i++;j--;
//            }
//        }
//
//    }

    static int[] lps(String p){
        int[] res = new int[p.length()];
        int i=1,j=0;
        res[0] = 0;
        while (i<p.length()){
            if(p.charAt(i) == p.charAt(j)){
                res[i++] = ++j;
            }else {
                if(j==0){
                    res[i] = 0;
                    i++;
                }else {
                    j = res[j-1];
                }
            }
        }
        return res;
    }

    private static String pad(String a, int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n-a.length(); i++) {
            sb.append("0");
        }
        sb.trimToSize();
        sb.append(a);
        return sb.toString();
    }
}
