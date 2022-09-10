public class Main {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("Создаю потоки и помещаю их в группу");
        ThreadGroup threadGroup = new ThreadGroup("Группа потоков");
        final MyThread myThread1 = new MyThread(threadGroup, "Поток 1");
        final MyThread myThread2 = new MyThread(threadGroup, "Поток 2");
        final MyThread myThread3 = new MyThread(threadGroup, "Поток 3");
        final MyThread myThread4 = new MyThread(threadGroup, "Поток 4");

        System.out.println("Запускаю все потоки:");
        myThread1.start();
        myThread2.start();
        myThread3.start();
        myThread4.start();

        Thread.sleep(15000);
        threadGroup.interrupt();
        Thread.sleep(500);
        System.out.printf("%s остановлена\n", threadGroup.getName());
    }
}
