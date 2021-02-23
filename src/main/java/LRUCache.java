import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LRUCache {

    class Node{
        int key;
        int value;
        Node next;
        Node prev;

        Node(int key,int value){
            this.key = key;
            this.value = value;
        }
    }

    Node head;
    Node tail;
    Map<Integer,Node> index;
    int maxSize;

    public LRUCache(int capacity) {
        maxSize = capacity;
        index = new HashMap<>();
    }

    Node unlink(Node node){
        Node prev = node.prev;
        Node next = node.next;
        if(prev == null){
            head = next;
        }else {
            prev.next = next;
        }
        node.next = null;

        if(next == null){
            tail = prev;
        }else {
            next.prev = prev;
        }
        node.prev = null;
        return node;
    }

    void linkFirst(Node node){
        node.next = head;
        if(head == null) {
            head = node;
            tail = node;
        }else{
            head.prev = node;
            head = node;
        }
    }

    Node unlinkLast(){
        Node last = tail;
        Node prev = tail.prev;
        tail = prev;
        if(prev == null)
            head = null;
        else
            prev.next = null;
        last.prev = null;
        return last;
    }

    public int get(int key) {
        if(index.containsKey(key)) {
            Node node = index.get(key);
            unlink(node);
            linkFirst(node);
            return node.value;
        }
        return -1;
    }

    public void set(int key, int value) {
        if(index.containsKey(key)){
            Node node = index.get(key);
            unlink(node);
            node.value = value;
            linkFirst(node);
        }else {
            if(index.size() >= maxSize){
                Node last = unlinkLast();
                index.remove(last.key);
            }
            Node node = new Node(key,value);
            index.put(key,node);
            linkFirst(node);
        }
    }
}
