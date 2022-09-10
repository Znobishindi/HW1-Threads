import java.util.Arrays;
import java.util.concurrent.RecursiveTask;

public class ValueSumCounter extends RecursiveTask<Integer> {
    private int[] mass;

    public ValueSumCounter(int[] mass) {
        this.mass = mass;
    }

    @Override
    protected Integer compute() {
        if (mass.length <= 2) {
            return Arrays.stream(mass).sum();
        }
        ValueSumCounter firstHalfArrayValueSumCounter = new ValueSumCounter(Arrays.copyOfRange(mass, 0, mass.length / 2));
        ValueSumCounter secondHalfArrayValueSumCounter = new ValueSumCounter(Arrays.copyOfRange(mass, mass.length / 2, mass.length));
        firstHalfArrayValueSumCounter.fork();
        secondHalfArrayValueSumCounter.fork();
        return firstHalfArrayValueSumCounter.join() + secondHalfArrayValueSumCounter.join();
    }
}
