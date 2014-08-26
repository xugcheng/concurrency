package exercise_21_2_2;

public class BasicThread {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		for(int i=0;i<5;i++)
		{
			Thread t = new Thread(new PrtTask());
			t.start();
		}
		System.out.println("active count:"+Thread.activeCount());
	}

}
