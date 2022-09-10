import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("Создаю Callable-задачи и помещаю их в HashSet");
        final Callable<Integer> callable1 = new MyCallable("Поток 1");
        final Callable<Integer> callable2 = new MyCallable("Поток 2");
        final Callable<Integer> callable3 = new MyCallable("Поток 3");
        final Callable<Integer> callable4 = new MyCallable("Поток 4");

        Set<Callable<Integer>> callables = new HashSet<>();
        callables.add(callable1);
        callables.add(callable2);
        callables.add(callable3);
        callables.add(callable4);

        System.out.println("Создаю пул потоков");
        final ExecutorService threadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        System.out.println("Запускаю потоки, беря их из пула, и добавляю результаты в List");
        List<Future<Integer>> futureList = threadPool.invokeAll(callables);
        System.out.println("Беру из List Future-значения и вывожу их в консоль, дожидаясь окончания каждого потока");
        for (Future<Integer> i : futureList){
            System.out.println(i.get());
        }

        Thread.sleep(2000);
        System.out.println("Запускаю потоки, беря их из пула, и добавляю результат первого завершившегося в Integer");
        final Integer first = threadPool.invokeAny(callables);
        System.out.println(first);
        threadPool.shutdown();



    }
}
