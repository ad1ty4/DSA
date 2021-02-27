import java.util.Comparator;
import java.util.PriorityQueue;

public class MedianInStream {

    private PriorityQueue<Integer> minHeap;
    private PriorityQueue<Integer> maxHeap;

    public MedianInStream() {
        maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        minHeap = new PriorityQueue<>();
    }

    public void addNum(int num) {
        if(maxHeap.isEmpty() || num <= maxHeap.peek())
            maxHeap.offer(num);
        else
            minHeap.offer(num);
        balance();
    }

    private void balance() {
        int diff = maxHeap.size() - minHeap.size();
        if(diff>1){
            Integer tmp = maxHeap.poll();
            minHeap.offer(tmp);
        }else if(diff<0){
            Integer tmp = minHeap.poll();
            maxHeap.offer(tmp);
        }
    }

    public double findMedian() {
        int maxHeapSize = maxHeap.size();
        int minHeapSize = minHeap.size();
        if(maxHeapSize>minHeapSize){
            return maxHeap.peek();
        }else {
            return 0.5 * (double) ((maxHeapSize>0?maxHeap.peek():0) + (minHeap.size()>0?minHeap.peek():0));
        }
    }
}
