import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class GUI extends JPanel {
    private static Timer timer,delay;
    public int a=400,b=350;
    public int c=40,d=470;
    public int posX,posY;

    private BufferedImage image1;
    private BufferedImage image2;
    private BufferedImage image3;
    private BufferedImage image4;

    private String  lokasiFile11 = "D:\\MyDoc\\Doo\\UPI\\Semester 6\\PBO\\PBO_TugasBesar_Kelompok1_2019-versi_2\\1.jpg";
    private String  lokasiFile12 = "D:\\MyDoc\\Doo\\UPI\\Semester 6\\PBO\\PBO_TugasBesar_Kelompok1_2019-versi_2\\2.png";
    private String  lokasiFile13 = "D:\\MyDoc\\Doo\\UPI\\Semester 6\\PBO\\PBO_TugasBesar_Kelompok1_2019-versi_2\\3.png";
    private String  lokasiFile14 = "D:\\MyDoc\\Doo\\UPI\\Semester 6\\PBO\\PBO_TugasBesar_Kelompok1_2019-versi_2\\4.jpg";

    public static Player oPlayer;
    public int jumlah=0;
    public GUI() {
        //border berwarna hitam di panel
        setBorder(BorderFactory.createLineBorder(Color.black));

        try {
            image1 = ImageIO.read(new File(lokasiFile11));
            image2 = ImageIO.read(new File(lokasiFile12));
            image3 = ImageIO.read(new File(lokasiFile13));
            image4 = ImageIO.read(new File(lokasiFile14));

        } catch (IOException ex) {
            // handle exception...
            System.out.println("Error load file!!");
        }
    }

    public Dimension getPreferredSize() {
        return new Dimension(200,200);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setFont(new Font("roboto", Font.BOLD, 15));

        g.drawString("Nama : "+oPlayer.nama,10,40);
        g.drawString("Kesehatan : "+oPlayer.getKesehatan(),10,80);
        g.drawString("Senjata : "+oPlayer.getNamaSenjataDigunakan(),10,100);
        g.drawString("Daftar Efek : "+oPlayer.getDaftarEfekDiri(),10,120);
        g.drawString("Nama Tempat : "+oPlayer.adeganAktif.namaTempat,10,140);
        g.drawString("Posisi : "+oPlayer.adeganAktif.posisiPlayer,260,140);


        g.drawString(oPlayer.adeganAktif.narasi,10,650);

        if (oPlayer.adeganAktif.idLantai == 3){
            g.drawImage(image3, 30, 170, 450,450, this);
        }else if(oPlayer.adeganAktif.idLantai == 2){
            g.drawImage(image2, 30, 170, 450,450, this);

        }else if(oPlayer.adeganAktif.idLantai == 4){
            g.drawImage(image4, 30, 170, 450,450, this);

        }else if(oPlayer.adeganAktif.idLantai == 1){
            g.drawImage(image1, 30, 170, 450,450, this);
        }


        posX = 210;
        posY = 200;
        g.drawString("1  ",posX,posY);
        posX = 210;
        posY = 410;
        g.drawString("2  ",posX,posY);
        posX = 210;
        posY = 582;
        g.drawString("3  ",posX,posY);
        posX = 120;
        posY = 478;
        g.drawString("4  ",posX,posY);
        posX = 380;
        posY = 530;
        g.drawString("5  ",posX,posY);
        posX = 210;
        posY = 544;
        g.drawString("6  ",posX,posY);
// //       posX = 380;
////        posY = 570;

        if (oPlayer.adeganAktif.idLantai == 1 ){
            if (oPlayer.adeganAktif.idPosisi == 3){
                posX = 210;
                posY = 582;
            }else if(oPlayer.adeganAktif.idPosisi == 2){
                posX = 210;
                posY = 410;
            }else if(oPlayer.adeganAktif.idPosisi == 1){
                posX = 210;
                posY = 200;
            }else if(oPlayer.adeganAktif.idPosisi == 6){
                posX = 210;
                posY = 544;
            }else if(oPlayer.adeganAktif.idPosisi == 4){
                posX = 120;
                posY = 478;
            }else if(oPlayer.adeganAktif.idPosisi == 5){
                posX = 380;
                posY = 530;
            }
        }else if (oPlayer.adeganAktif.idLantai == 2){
            if (oPlayer.adeganAktif.idPosisi == 3){
                posX = 210;
                posY = 582;
            }else if(oPlayer.adeganAktif.idPosisi == 2){
                posX = 210;
                posY = 410;
            }else if(oPlayer.adeganAktif.idPosisi == 1){
                posX = 210;
                posY = 200;
            }else if(oPlayer.adeganAktif.idPosisi == 6){
                posX = 210;
                posY = 544;
            }else if(oPlayer.adeganAktif.idPosisi == 4){
                posX = 120;
                posY = 478;
            }else if(oPlayer.adeganAktif.idPosisi == 5){
                posX = 380;
                posY = 530;
            }else if(oPlayer.adeganAktif.idPosisi == 7){
                posX = 410;
                posY = 510;
            }
        }
        g.setColor(Color.BLUE);
        g.fillOval(posX, posY,  20, 20);

        g.setColor(Color.green);
        g.fillOval(430, 350,  20, 20);

        g.setColor(Color.RED);
        g.fillOval(210, 410,  20, 20);

    }


}