import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an array with +ve and -ve elements.Find
 * 1) Max sum of any subarray (Kadane's)
 * 2) Print subarray with max sum
 * 3) Check of a subarray exists with given sum
 * 4) Print subarray of given sum
 *
 *
 */

public class SlidingWindow {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(-2, -3, 4);
        List<Integer> list1 = Arrays.asList(-2, -3, 4, -1, -2, 1, 5, -3);
        System.out.println(maxLenSubArray(list1));
    }

    static int res=0;
    static int maxSubArraySum(List<Integer> list,int index){
        if(index<0)
            return 0;
        int sumEndingHere = maxSubArraySum(list,index-1);
        if(sumEndingHere<0)
            return 0;
        res = Math.max(res,sumEndingHere+list.get(index));
        return sumEndingHere + list.get(index);
    }

    static int maxSubArraySum(List<Integer> list){
        int maxEndingHere=list.get(0),maxSum=list.get(0);
        for (int i = 1; i <list.size() ; i++) {
            maxEndingHere = Math.max(list.get(i),maxEndingHere + list.get(i));
            maxSum = Math.max(maxSum,maxEndingHere);
        }
        return maxSum;
    }

    static int maxLenSubArray(List<Integer> list){
        int maxEndingHere=list.get(0),maxSum=Integer.MIN_VALUE,s=0,e=0,j=0;
        for (int i = 0; i <list.size() ; i++) {
            maxEndingHere+=list.get(i);
            if(maxEndingHere<0){
                maxEndingHere=0;
                j = i;
            }
            if(maxSum<maxEndingHere){
                maxSum = maxEndingHere;
                s = j+1;
                e = i;
            }
        }
        System.out.println(s+" "+e);
        return maxSum;
    }


}
