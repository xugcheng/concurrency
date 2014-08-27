package shareresource;

public class EvenGenerator extends IntGenerator {
	private int currentEvenValue=0;
	@Override
	public int next() {
		currentEvenValue++;//danger point
		currentEvenValue++;
		return currentEvenValue;
	}
	
	public static void main(String[] args){
		EvenChecker.test(new EvenGenerator());
	}
	
}
