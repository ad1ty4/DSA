import java.util.ArrayList;

public class SpiralOrder {

    public int[] spiralOrder(final int[][] a) {
        int m = a.length;
        if(m==0)
            return new int[0];
        int n = a[0].length;
        int i=0,j=0,t=0,b=m-1,l=0,r=n-1,dir=0,k=0;
        int[] res = new int[m*n];
        while (l<=r && t<=b){
            if(dir==0){
                for (j = l; j <= r; j++) {
                    res[k++] = a[i][j];
                }
                dir = 1;
                t++;
                j--;
            }else if(dir==1){
                for ( i = t; i <= b; i++) {
                    res[k++] = a[i][j];
                }
                dir=2;
                r--;
                i--;
            }else if(dir==2){
                for ( j = r; j >= l ; j--) {
                    res[k++] = a[i][j];
                }
                dir=3;
                b--;
                j++;
            }else {
                for (i = b; i >= t ; i--) {
                    res[k++] = a[i][j];
                }
                dir=0;
                l++;
                i++;
            }
        }
        return res;
    }
}
