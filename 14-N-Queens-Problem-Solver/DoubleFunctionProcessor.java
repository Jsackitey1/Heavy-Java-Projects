import java.util.function.DoubleFunction;

public class DoubleFunctionProcessor {
	private double value;
	
	public DoubleFunctionProcessor(double initValue) {
		this.value = initValue;
	}

	
	public void process(DoubleFunction<Double> function) {
		synchronized (this) {
			this.value = function.apply(value);
		}
	}

	public double getValue() {
		synchronized (this) {
			return this.value;
		}
	}
}
