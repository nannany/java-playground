import java.util.concurrent.ConcurrentHashMap;

public class Main {
    //    public static HashMap<String, Integer> map = new HashMap<>();
    public static ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();

    static {
        map.put("key", 1);
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new MyTask());
        thread1.start();
        Thread thread2 = new Thread(new MyTask());
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println(map.get("key"));

    }

}

class MyTask implements Runnable {
    public void run() {
        for (int i = 0; i < 100; i++) {
            Main.map.computeIfPresent("key", (key, value) -> value + 1);
        }
    }
}
