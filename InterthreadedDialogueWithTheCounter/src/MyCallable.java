import java.util.concurrent.Callable;

public class MyCallable extends Thread implements Callable {

    public MyCallable(String name) {
        super(name);
    }

    @Override
    public Object call() {
        int n = 0;
        for (int i = 0; i < 5; i++) {
            n++;
            try {
                Thread.sleep(1000);
                System.out.printf("Я %s. Всем привет!(Я говорю это в %dй раз)\n", getName(), n);
            } catch (InterruptedException e) {
                break;
            }
        }
        System.out.printf("%s вывел в консоль %d сообщений\n", getName(), n);
        return n;
    }
}
