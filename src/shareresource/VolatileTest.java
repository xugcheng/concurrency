package shareresource;

import java.util.concurrent.TimeUnit;

class Counter{
	public static volatile int count=0;
	public static void inc(){
		try {
			TimeUnit.MILLISECONDS.sleep(1);
		} catch (InterruptedException e) {
			System.out.println("sleep() interrupted.");
		}
		count++;
	}
}

public class VolatileTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for(int i=0;i<1000;i++){
			new Thread(new Runnable() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					Counter.inc();
				}
			}).start();
		}
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Counter.count="+Counter.count);
	}

}
