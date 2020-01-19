import org.jfugue.Player;

import java.awt.*;

public class MouseMusic implements Runnable {
    public final Object lock = new Object();

    public MouseMusic() {

    }

    public void run() {
        //noinspection InfiniteLoopStatement
        while (true) {
            synchronized (this.lock) {
                while (MouseChecker.m) {
                    try {
                        this.lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            Player p = new Player();
            Point loc = MouseInfo.getPointerInfo().getLocation();
            Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
            double screenHeight = d.getHeight();
            double screenWidth = d.getWidth();
            int var8 = 127 - (int) ((double) (loc.y * 127) / screenHeight);
            int var9 = (int) ((double) (loc.x * 16383) / screenWidth);
//            p.play("X[Volume]=" + var9 + " [" + var8 + "]");
            System.out.println(String.format("volume is %d and pitch is %d", var9, var8));
            p.play(String.format("X[Volume]=%d [%d]", var9, var8));
//            p.play(":CON("+var9+","+var8+")");
//            p.play("C D E F G A B");
        }
    }
}
