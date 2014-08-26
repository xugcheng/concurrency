package daemon;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DaemonFromFactory implements Runnable{

	@Override
	public void run() {
		try {
			TimeUnit.MILLISECONDS.sleep(100);
			System.out.println(Thread.currentThread()+" "+this);
		} catch (InterruptedException e) {
			System.out.println("sleep() Interrupted.");
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool(new DaemonThreadFactory());
		for(int i=0;i<10;i++)
		{
			exec.execute(new DaemonFromFactory());
		}
		exec.shutdown();
		System.out.println("All daemons started.");
		try {
			TimeUnit.MILLISECONDS.sleep(99);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	

}
