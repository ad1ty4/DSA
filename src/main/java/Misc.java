import java.util.*;

public class Misc {

    public static void main(String[] args) {
        System.out.println(intToRoman(9));
    }

    public static String intToRoman(int a) {
        Map<Integer, String> map = new LinkedHashMap<>();
        map.put(1000, "M");
        map.put(900, "CM");
        map.put(500, "D");
        map.put(400, "CD");
        map.put(100, "C");
        map.put(90, "XC");
        map.put(50, "L");
        map.put(40, "XL");
        map.put(10, "X");
        map.put(9, "IX");
        map.put(5, "V");
        map.put(4, "IV");
        map.put(1, "I");
        StringBuilder sb = new StringBuilder();
        while (a > 0) {
            int key = findClosestChar(a, map.keySet());
            sb.append(map.get(key));
            a -= key;
        }
        return sb.toString();
    }




    private static int findClosestChar(int a, Set<Integer> keySet) {
        for (Integer v : keySet) {
            if (a / v >= 1)
                return v;
        }
        return 1;
    }

    public int maxPoints(ArrayList<Integer> a, ArrayList<Integer> b) {
        int n = a.size();
        int maxPoints = 0;
        for (int i = 0; i < n; i++) {
            Map<Double, Integer> map = new HashMap<>();
            for (int j = i; j < n; j++) {
                Double slope = findSlope(a.get(i), b.get(i), a.get(j), b.get(j));
                map.put(slope, map.getOrDefault(slope, 0) + 1);
                maxPoints = Math.max(maxPoints, map.get(slope));
            }
        }
        return maxPoints;
    }

    private Double findSlope(Integer x1, Integer y1, Integer x2, Integer y2) {
        return (double) (y2 - y1) / (x2 - x1);
    }

}
