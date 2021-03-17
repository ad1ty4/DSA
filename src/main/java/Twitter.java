
/*
The Active Twitterati

Implement a feature active_twitterati which simulates the following operations.

active_twitterati has two functions:

store , which pushes a twitterati_id on a data structure whenever a user tweets.

find_best_twitterati, which removes and returns the most frequent twitterati_id in the structure.

If there is a tie for the most frequent twitterati_id, the most recent twitterati_id is removed and returned.
*/

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Twitter {

    Map<String,Integer> map;
    LinkedList<String> queue;

    Twitter(){
        map = new HashMap<>();
        queue = new LinkedList<>();
    }

    String find_best_twitterati(){
        return "";
    }

    void store(String userId){
        map.put(userId,map.getOrDefault(userId,0) + 1);
        queue.offer(userId);
    }
}
