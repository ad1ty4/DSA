import java.util.ArrayList;

/**
 * Two Pointer Techinique
 *
 *
 */

public class RemoveDuplicatesGeneric {

    public static void main(String[] args) {
    }

    public static int removeDuplicates(ArrayList<Integer> a) {
        return removeDuplicatesGeneric(a,1);
    }

    public static int removeDuplicatesGeneric(ArrayList<Integer> a, int k){
        if(a.size()<=k)
            return a.size();
        int j=k-1;
        for (int i = k; i <a.size() ; i++) {
            if(!a.get(i).equals(a.get(j-k+1))){
                a.set(++j,a.get(i));
            }
        }
        return j+1;
    }
}
