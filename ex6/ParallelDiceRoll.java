//Joseph Sackitey
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ParallelDiceRoll {

	public ParallelDiceRoll() {

	}

	public static void assignRandomRollsSerial(int[] rolls) {
		Random random = new Random();
		for (int i = 0; i < rolls.length; i++) {
			rolls[i] = random.nextInt(6) + 1;
		}
	}

	public static void assignRandomRollsParallel(int[] rolls) {
		int numProcessors = Runtime.getRuntime().availableProcessors();
		int partitions = rolls.length / numProcessors;

		ExecutorService executor = Executors.newFixedThreadPool(numProcessors);

		for (int i = 0; i < numProcessors; i++) {
			final int start = i * partitions;
			final int stop = (i == numProcessors - 1) ? rolls.length : (i + 1) * partitions;

			executor.submit(() -> {
				Random random = new Random();
				for (int j = start; j < stop; j++) {
					rolls[j] = random.nextInt(6) + 1;
				}
			});
		}

		executor.shutdown();
		try {
			executor.awaitTermination(10, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

	public static void main(String[] args) {
		int size = 1234567; 
		int[] rolls = new int[size];

		long ms = System.currentTimeMillis();
		assignRandomRollsSerial(rolls);
		System.out.printf("Serial assignment: %d ms\n", System.currentTimeMillis() - ms);

		ms = System.currentTimeMillis();
		assignRandomRollsParallel(rolls);
		System.out.printf("Parallel assignment: %d ms\n", System.currentTimeMillis() - ms);
	}
}
