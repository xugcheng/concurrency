
public class BasicThreads {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Thread t = new Thread(new LiftOff()); //2.以新建Thread方式,启动任务
		t.start();
		System.out.println("waiting for LiftOff!");
	}

}
