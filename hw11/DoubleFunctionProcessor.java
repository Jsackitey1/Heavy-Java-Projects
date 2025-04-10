import java.util.function.DoubleFunction;

public class DoubleFunctionProcessor {
	private double value;
	
	public DoubleFunctionProcessor(double initValue) {
		this.value=initValue;
		
	}
	
	public void process(java.util.function.DoubleFunction<java.lang.Double> function) {
		double results= function.apply(value);
		this.value=results;
	}
	
	public double getValue() {
		return this.value;
	}

}
