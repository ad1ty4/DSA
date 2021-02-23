import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

public class BookingCom {

    public static void main(String[] args) {
        Map<Integer,Pair> map = new HashMap<>();
        map.put(1,new Pair(1,1));

        // sort by key


        //sort by value and get keyset
        Collection<Integer> res1  = map.entrySet()
                .stream().sorted(Comparator.comparing(Map.Entry::getValue))
                .collect(Collectors.toMap(k -> k.getKey(), v -> v.getValue(), (e1,e2) -> e1, LinkedHashMap::new)).keySet();

        //sort by key and get values
        Collection<Pair> res2  = map.entrySet()
                .stream().sorted(Comparator.comparing(Map.Entry::getKey))
                .collect(Collectors.toMap(k -> k.getKey(), v -> v.getValue(), (e1,e2) -> e1, LinkedHashMap::new)).values();

        //sort by key and get keyset
        Collection<Integer> res3 = map.keySet()
                .stream().sorted(Comparator.comparingInt(v-> v)).collect(Collectors.toList());

        //sort by value and get values
        Collection<Pair> res4 = map.values()
                .stream().sorted(Comparator.comparing(v -> v.hotelId)).collect(Collectors.toList());

    }

    static class Pair implements Comparable<Pair>{
        int hotelId;
        int score;

        public Pair(int hotelId, int score) {
            this.hotelId = hotelId;
            this.score = score;
        }

        @Override
        public int compareTo(Pair o) {
            return o.score;
        }
    }

//    public static List<Integer> awardTopKHotels(String positiveKeywords, String negativeKeywords, List<Integer> hotelIds, List<String> reviews, int k) {
//        Set<String> positiveSet = Arrays.stream(positiveKeywords.split(" ")).map(String::toLowerCase).collect(Collectors.toSet());
//        Set<String> negativeSet = Arrays.stream(negativeKeywords.split(" ")).collect(Collectors.toSet());
//
//        Map<Integer,Integer> map = new HashMap<>();
//        for (int i=0;i<hotelIds.size();i++) {
//            if(map.containsKey(hotelIds.get(i)))
//                map.put(hotelIds.get(i),map.get(hotelIds.get(i)) + findScore(reviews.get(i),positiveSet,negativeSet));
//            else
//                map.put(hotelIds.get(i),findScore(reviews.get(i),positiveSet,negativeSet));
//        }
//        Map<Integer,Integer> resMap = map.entrySet().stream()
//                .sorted((o1, o2) -> o2.getValue().compareTo(o1.getValue()))
//                .limit(k)
//                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
//        return new ArrayList<>(resMap.keySet());
//    }

    private static int findScore(String review, Set<String> positive, Set<String> negative) {
        int score=0;
        for (String r: review.split(" ")) {
            r = r.toLowerCase();
            r = r.replaceAll("[.]","");
            if(positive.contains(r))
                score+=3;
            else if(negative.contains(r))
                score-=1;
        }
        return score;
    }

//    public static long carParkingRoof(List<Long> cars, int k) {
//        // Write your code here
//        if(k>=cars.size())
//            return cars.size();
//        List<Long> sortedCars = cars.stream().sorted().collect(Collectors.toList());
//        long res=Long.MAX_VALUE;
//        for (int i = k-1,j=0; i <sortedCars.size() ; i++,j++) {
//            res = Math.min(res,sortedCars.get(i) - sortedCars.get(j) + 1);
//        }
//        return res;
//    }

//    public static List<String> funWithAnagrams(List<String> text) {
//        // Write your code here
//        Set<String> set = new HashSet<>();
//        List<String> res = new ArrayList<>();
//        for (String t: text) {
//            char[] tmp = t.toCharArray();
//            Arrays.sort(tmp);
//            String tmpStr = String.valueOf(tmp);
//            if(!set.contains(tmpStr)) {
//                res.add(t);
//                set.add(tmpStr);
//            }
//        }
//        Collections.sort(res);
//        return res;
//    }
}
