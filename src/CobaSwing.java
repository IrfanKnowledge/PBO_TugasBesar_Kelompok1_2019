import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.*;

public class CobaSwing {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        //Buat frame
        JFrame f = new JFrame("Coba Hello");
        //exit jika window ditutup
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //tambahkan panel
        f.add(new MyPanel());
        //pack semua komponen dalam panel
        f.pack();
        //set ukuran panel dalam pixel: lebar, tinggi
        f.setSize(500,500);
        f.setVisible(true);
    }

}
