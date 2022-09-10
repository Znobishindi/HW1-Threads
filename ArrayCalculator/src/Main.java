import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Выполняем задачу в одном потоке");

        int[] mass = createMass(10000000);

        long beginning = System.nanoTime();

        int length = mass.length;

//        long sum = sumOfMassElements(mass, length - 1);
        long sum = sumOfMassElements1(mass);

        double average = (double) sum / mass.length;
        long ending = System.nanoTime();
        long time1 = ending - beginning;
        System.out.println("Сумма всех элементов массива, длиной " + mass.length + " ячеек составляет " + sum + ", а среднее арифметическое " + average);
        System.out.println("Процесс в одном потоке занял " + time1 + " наносекунд");

        Thread.sleep(2000);

        System.out.println("Выполняем задачу, используя RecursiveTask");
        beginning = System.nanoTime();
        ValueSumCounter counter = new ValueSumCounter(mass);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        sum = forkJoinPool.invoke(counter);
        average = (double) sum / mass.length;
        ending = System.nanoTime();
        long time2 = ending - beginning;
        System.out.println("Сумма всех элементов массива, длиной " + mass.length + " ячеек составляет " + sum + ", а среднее арифметическое " + average);
        System.out.println("Процесс, использующий RecursiveTask занял " + time2 + " наносекунд");
        System.out.println(time2/time1);

    }

    public static int[] createMass(int massSize) {
        int[] mass = new int[massSize];
        for (int i = 0; i < mass.length; i++) {
            mass[i] = (int) (Math.random() * 10);
        }
        return mass;
    }

    public static long sumOfMassElements(int[] mass, int length) {
        if (length == 0) {
            return mass[0];
        } else {
            return mass[length] + sumOfMassElements(mass, length - 1);
        }
    }
    public static long sumOfMassElements1(int[] mass){
        int sum = 0;
        for (int i = 0; i < mass.length; i++) {
            sum += mass[i];
        }
        return sum;
    }

}
