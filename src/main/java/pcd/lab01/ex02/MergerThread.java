package pcd.lab01.ex02;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

public class MergerThread extends Thread{

    private int[] partition1;
    private int[] partition2;

    private ArrayList<Integer> result = new ArrayList<>();

    MergerThread(int[] partition1, int[] partition2){
        this.partition1 = partition1;
        this.partition2 = partition2;
    }

    public void run(){
        var merging = true;
        var index1 = 0; var index2 = 0;
        while(merging){
            if(partition1[index1] <= partition2[index2]){
                result.add(partition1[index1]);
                index1++;
            }
            else{
                result.add(partition2[index2]);
                index2++;
            }

            if(index1 == partition1.length){
                for (int i = index2; i < partition2.length; i++) {
                    result.add(partition2[i]);
                }
                merging = false;
                interrupt();
            }

            if(index2 == partition2.length){
                for (int i = index1; i < partition2.length; i++) {
                    result.add(partition1[i]);
                }
                merging = false;
                interrupt();
            }
        }

    }

    public ArrayList<Integer> getResult(){
        return this.result;
    }

}
