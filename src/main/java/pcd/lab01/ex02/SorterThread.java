package pcd.lab01.ex02;

import java.util.Arrays;

public class SorterThread extends Thread{
    private final int[] vectorPartition;
    private final int threadId;

    SorterThread(final int[] partition, final int threadId){
        this.vectorPartition = partition;
        this.threadId = threadId;
    }

    public void run(){
        Arrays.sort(vectorPartition, 0, vectorPartition.length);
        log("Finished Sorting");
    }

    public int[] getVectorPartition(){
        return this.vectorPartition;
    }

    private void log(String msg) {
        System.out.println("[ " + System.currentTimeMillis() +   " ][ " + this.threadId + " ] " + msg);
    }
}
