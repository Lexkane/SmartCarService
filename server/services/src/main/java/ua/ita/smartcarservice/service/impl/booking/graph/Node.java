package ua.ita.smartcarservice.service.impl.booking.graph;

import java.util.Comparator;

public class Node implements Comparator {
    private long index;
    private long requiredTime;

    public Node(long index, long requiredTime){
        this.index = index;
        this.requiredTime = requiredTime;
    }

    public long getIndex() {
        return index;
    }

    public long getRequiredTime() {
        return requiredTime;
    }
    @Override
    public int compare(Object o1, Object o2) {
        Node e1 = (Node)o1;
        Node e2 = (Node)o2;

        return Long.compare(e1.requiredTime, e2.requiredTime)* -1;
    }
}
