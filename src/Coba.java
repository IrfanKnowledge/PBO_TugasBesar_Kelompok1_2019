import java.util.ArrayList;

//class percobaan saja, tidak untuk project ini
public class Coba extends Thread{
    String mencoba;
    int time;
    Coba(String mencoba){
        this.mencoba = mencoba;
    }
    Coba(int i){
        this.time = i;
    }

    public void run() {
//        time++;
//        System.out.println(time);
//        if(time == 24){
//            time = 1;
//        }
//        (new Coba(time)).start();

        ArrayList<Coba> test = new ArrayList<>();
        Coba hello= new Coba(19);
        test.add(hello);
        test.remove(hello);
        System.out.println(test);
        ArrayList<String> lp = new ArrayList<>();
        lp.add("10");
        lp.remove("10");
        System.out.println(lp);
    }

    public static void main(String args[]) {
        (new Coba(1)).start();
    }
}
