package shareresource;

public class SynchronizeEvenGenerator extends IntGenerator {

	private int currentEvenValue=0;
	@Override
	public synchronized int next() {
		currentEvenValue++;
		Thread.yield();
		currentEvenValue++;
		return currentEvenValue;
	}
	
	public static void main(String[] args){
		EvenChecker.test(new SynchronizeEvenGenerator());
	}
	
}