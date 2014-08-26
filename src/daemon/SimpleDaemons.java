package daemon;

import java.util.concurrent.TimeUnit;

public class SimpleDaemons implements Runnable {

	@Override
	public void run() {
		try {
			TimeUnit.MILLISECONDS.sleep(176);
			System.out.println(Thread.currentThread()+" "+this);
		} catch (InterruptedException e) {
			System.out.println("sleep() Interruped.");
		}
	}
	
	public static void main(String[] args)
	{
		for(int i=0;i<10;i++)
		{
			Thread daemonThread = new Thread(new SimpleDaemons());
			daemonThread.setDaemon(true);
			daemonThread.start();
		}
		System.out.println("All daemons started.");
		try {
			TimeUnit.MILLISECONDS.sleep(175);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
