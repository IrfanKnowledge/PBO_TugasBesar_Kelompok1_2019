import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;

public class Thread1 extends Thread {
    private int rand;
    public static Player oPlayer;
    //Buat frame
    private static JFrame f = new JFrame("Ladang1111111.CSE");
    private static GUI g = new GUI();

    private void createAndShowGUI() {
        //exit jika window ditutup
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //tambahkan panel
        f.add(g);
        //pack semua komponen dalam panel
        f.pack();
        //set ukuran panel dalam pixel: lebar, tinggi
        f.setSize(1000,1000);
        f.setVisible(true);

        Timer timer = new Timer(1000, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                refresh();
                run();
            }
        });
        timer.start();

    }
    public Thread1(){
        createAndShowGUI();
    }

    private void refresh(){
        f.getContentPane().remove(g);
        f.getContentPane().invalidate();
        f.getContentPane().add(g);
        f.getContentPane().revalidate();
        f.repaint();

    }

    public void run() {
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        //refresh();
    }
}

