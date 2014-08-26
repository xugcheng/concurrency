package daemon;

import java.util.concurrent.TimeUnit;

class ADaemon implements Runnable
{

	@Override
	public void run() {
		
		try {
			System.out.println("Start ADaemon.");
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			System.out.println("sleep() Interrupted.");
		} finally{
			System.out.println("This should always run?");
		}
	}
	
}


public class DaemonsDontRunFinally {

	
	public static void main(String[] args) {
		Thread t = new Thread(new ADaemon());
		t.setDaemon(true); //守护线程,在非守护线程(本例中非守护线程是main主线程)都结束以后,自动停止运行.
		t.start();
	}
	
}
