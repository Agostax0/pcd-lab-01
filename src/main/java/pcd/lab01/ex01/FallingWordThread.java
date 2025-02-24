package pcd.lab01.ex01;

import org.fusesource.jansi.Ansi;

public class FallingWordThread extends Thread{

    private final Screen screen;
    private final String word;
    private final int threadId;
    private final int speed;

    private boolean stopped = false;

    private int x0;
    private int y0 = 10;

    FallingWordThread(final Screen mainScreen, final String word, final int threadId, final int threadX0){
        this.screen = mainScreen;
        this.word = word;
        this.threadId = threadId;
        this.speed = (int)(1 +  Math.random() * 3);
        this.x0 = threadX0 + 1;
    }

    public void run(){
        while(!stopped){
            screen.writeStringAt(this.y0, this.x0, Ansi.Color.YELLOW, this.word);
            try {
                Thread.sleep(1000);
                screen.writeStringAt(this.y0, this.x0, Ansi.Color.BLACK, this.word);
            } catch (InterruptedException e) {
                System.out.println("[ " + System.currentTimeMillis() +   " ][ " + this.threadId+ " ] Interrupted ");
            }
            this.y0 += this.speed;
        }
    }

    public void notifyStop(){
        this.stopped = true;
        interrupt();
    }

}
