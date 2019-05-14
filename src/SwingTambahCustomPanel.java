import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

//extend JFrame
public class SwingTambahCustomPanel extends JFrame {
    private MyPanel2 panel2;

    //constructor
    public SwingTambahCustomPanel() {
        setTitle("Swing plus Custom Panel");

        //panel tempat lingkaran digambar
        panel2 = new MyPanel2();
        panel2.setPreferredSize(new Dimension(1000, 2000));

        //panel yg berisi button
        //menggunakan flow layout, artinya komponen akan diletakan dari kiri kanan lalu atas ke bawah
        //swing menyediakan banyak layout lain: GridLayout, BoxLayout
        JPanel btnPanel = new JPanel(new FlowLayout());

        //komponen label, JLabel adalah komponen standard Swing.
        JLabel labelInfo = new JLabel();
        labelInfo.setText("Klik button berikut untuk memperbesar");
        //tambah label ke panel
        btnPanel.add(labelInfo);

        //komponen button, yang juga standard Swing
        JButton btnGambar = new JButton("Perbesar!");
        btnPanel.add(btnGambar);
        //tambah aksi jika tombol ditekan
        btnGambar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                panel2.ukuranLingkaran = 500; //perbesar ukuran
                panel2.repaint(); // gambar ulang panel
                requestFocus();
            }
        });

        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        //tambah panel2 di tengah
        cp.add(panel2, BorderLayout.CENTER);
        //tambah panel berisi button di bagian bawah (selatan)
        cp.add(btnPanel, BorderLayout.PAGE_END);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
        requestFocus();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new SwingTambahCustomPanel();
            }
        });
    }
}
