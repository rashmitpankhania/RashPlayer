
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MouseChecker extends JPanel implements MouseListener, Runnable {
    private static final long serialVersionUID = 1L;

    public static boolean m = true;
    public MouseMusic music;
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    public void mousePressed(MouseEvent mouseEvent) {
        m = false;
        synchronized (this.music.lock){
            this.music.lock.notifyAll();
        }

    }

    public void mouseReleased(MouseEvent mouseEvent) {
        m = true;
        synchronized (this.music.lock){
            this.music.lock.notifyAll();
        }

    }

    public void mouseEntered(MouseEvent mouseEvent) {

    }

    public void mouseExited(MouseEvent mouseEvent) {

    }

    public void run() {
        JFrame v = new JFrame("RashPlayer");
        v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setOpaque(true);
        v.setContentPane(this);
        v.setExtendedState(JFrame.MAXIMIZED_BOTH);
        v.pack();
        v.setVisible(true);
        this.addMouseListener(this);
        this.music = new MouseMusic();
        (new Thread(this.music)).start();
    }
}
