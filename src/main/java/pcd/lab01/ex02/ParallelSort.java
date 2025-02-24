package pcd.lab01.ex02;

import java.util.Arrays;
import java.util.Random;

public class ParallelSort {

    static final int VECTOR_SIZE = 400_000_000;

    public static void main(String[] args) {

        log("Generating array...");
        var v = genArray(VECTOR_SIZE);

        log("Array generated.");
        log("Sorting (" + VECTOR_SIZE + " elements)...");

        var s1 = new SorterThread(Arrays.copyOfRange(v, 0, v.length/2 -1), 1);
        var s2 = new SorterThread(Arrays.copyOfRange(v, v.length/2, v.length), 1);

        var t0 = System.nanoTime();

        s1.start();
        s2.start();

        try {
            s1.join();
            s2.join();

            var mg = new MergerThread(s1.getVectorPartition(), s2.getVectorPartition());


            mg.start();
            mg.join();
            var t1 = System.nanoTime();

            var res = mg.getResult();
            var ordered = true;
            for(int i = 0; i < res.size(); i++){
                if(i != res.size()-1){
                    ordered = res.get(i) < res.get(i + 1) && ordered;
                }
            }

            if(ordered){
                log("Ordered");
            }
            else{
                log("Fail");
                //dumpArray(res.toArray());
            }

        }
        catch (InterruptedException e){
            e.printStackTrace();
        }

        // dumpArray(v);
    }


    private static int[] genArray(int n) {
        Random gen = new Random(System.currentTimeMillis());
        var v = new int[n];
        for (int i = 0; i < v.length; i++) {
            v[i] = gen.nextInt();
        }
        return v;
    }

    private static void dumpArray(Object[] v) {
        for (var l:  v) {
            System.out.print(l + " ");
        }
        System.out.println();
    }

    private static void log(String msg) {
        System.out.println(msg);
    }
}
