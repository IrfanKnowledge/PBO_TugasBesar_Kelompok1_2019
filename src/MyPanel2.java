import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class MyPanel2 extends JPanel {
    //atribut lingkaran
    public int ukuranLingkaran=75;
    public int posX = 200;
    public int posY = 50;

    public MyPanel2() {
        //border berwarna hitam di panel
        setBorder(BorderFactory.createLineBorder(Color.black));
    }

    public Dimension getPreferredSize() {
        return new Dimension(500,200);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Random posisi = new Random();
        int oPosisi = posisi.nextInt(500);
        System.out.println(posisi);
        g.setColor(Color.RED);
        for(int i=0; i<10; i++){
            g.fillOval(oPosisi, oPosisi,  ukuranLingkaran,ukuranLingkaran);
        }
    }

}
