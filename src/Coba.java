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
        time++;
        System.out.println(time);
        if(time == 24){
            time = 1;
        }
        (new Coba(time)).start();
    }

    public static void main(String args[]) {
        (new Coba(1)).start();
    }
}
