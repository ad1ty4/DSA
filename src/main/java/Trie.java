import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Trie {

    TrieNode root = new TrieNode(null);

    static class TrieNode {
        Character ch;
        Map<Character,TrieNode> children;
        Boolean isEnd;

        public TrieNode(Character ch) {
            this.ch = ch;
            this.children = new HashMap<>();
        }
    }

    void add(String str){
        TrieNode tmp = root;
        for (int i = 0; i < str.length() ; i++) {
            tmp.children.putIfAbsent(str.charAt(i),new TrieNode(str.charAt(i)));
            tmp = tmp.children.get(str.charAt(i));
        }
        tmp.isEnd = true;
    }

    boolean check(String str){
        TrieNode tmp = root;
        for (int i = 0; i < str.length() && tmp!=null ; i++) {
            tmp = tmp.children.get(str.charAt(i));
        }
        return tmp != null && tmp.isEnd;
    }

    public ArrayList<Integer> solve(String a, ArrayList<String> b) {
        ArrayList<Integer> res = new ArrayList<>();
        for (String input: a.split("_"))
            add(input);
        Map<Integer,Integer> scoreMap = new HashMap<>();
        int index=0;
        for (String input: b) {
            int score=0;
            for (String in: input.split("_")) {
                if(check(in))
                    score++;
            }
            scoreMap.put(index++,score);
        }
        scoreMap.entrySet().stream()
                .sorted(Comparator.comparing(Map.Entry::getValue,Comparator.reverseOrder()))
                .map(Map.Entry::getKey)
                .forEach(res::add);
        return res;
    }

}
