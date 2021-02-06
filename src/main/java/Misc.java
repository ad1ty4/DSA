public class Misc {

    public static void main(String[] args) {
//        System.out.println(solve(String.valueOf(999)));
        System.out.println(Misc.class.getGenericSuperclass());
    }

    public static int isPalindrome(int n) {
        if(n<0)
            return 0;
        String str = String.valueOf(n);
        for (int i = 0,j=str.length()-1; i < str.length(); i++,j--) {
            if(str.charAt(i)!=str.charAt(j))
                return 0;
        }
        return 1;
    }

//    static int reverse(String n){
//        int res=0;
//        while (n>0){
//            res*=10;
//            res+=n%10;
//            n/=10;
//        }
//        return res;
//    }
//
//    public static String solve(String n) {
//        int len = n.length();
//        if(len%2==0){
//            int first = Integer.parseInt(n.substring(0,len/2));
//            int second = reverse(Integer.parseInt(n.substring(len/2)));
//            if(second>=first) {
//                return createPalin(first);
//            } else
//                return String.valueOf(first) + reverse(first);
//        }else {
//            int first = Integer.parseInt(n.substring(0,len/2));
//            int mid = Integer.parseInt(n.substring(len/2,len/2+1));
//            int second = reverse(n.substring(len/2+1));
//            if(second>=first){
//                int firstMod = Integer.parseInt(n.substring(0,len/2+1));
//                if(mid!=9)
//                    return String.valueOf(first) + mid+1 + reverse(first);
//                else {
//                    int firstModMod = Integer.parseInt(n.substring(0,len/2+1)) + 1;
//                    int firstModFirst = String.valueOf(firstModMod);
//                    int secondModSecond = reverse(Integer.parseInt(n.substring(len/2+1)));
//                    return String.valueOf(firstModMod) + String.valueOf(secondModSecond);
//                }
//            }else {
//                return String.valueOf(first) + mid + reverse(first);
//            }
//        }
//    }
//
//    private static String createPalin(int n) {
//        int len = String.valueOf(n).length();
//        int modLen = String.valueOf(n+1).length();
//        if(len==modLen){
//            return String.valueOf(n) + reverse(n);
//        }else {
//            StringBuilder sb = new StringBuilder();
//            sb.append(n+1).reverse();
//            return String.valueOf(n+1).substring(0,len) + sb.toString();
//        }
//    }
}
