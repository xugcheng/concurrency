package atomic;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * getValue()方法是具有原子性的,但是依然不能保证同步.
 * @author xugc
 *
 */
public class AtomicityTest implements Runnable{
	private int i;
	
	public /*synchronized*/ int getValue(){
		return i;
	}

	public  synchronized void evenIncrement(){
		i++;
		Thread.yield();
		i++;
	}
	
	@Override
	public void run() {
		while(true){
			evenIncrement();
		}
	}
	
	public static void main(String[] args) {
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				System.out.println("abort.");
				System.exit(0);
			}
		}, 5000);
		
		AtomicityTest atomicityTest = new AtomicityTest();
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(atomicityTest);
		exec.shutdown();
		while(true){
			int val=atomicityTest.getValue();
			if(val%2!=0){
				System.out.println(val);
				System.exit(0);
			}
		}
		
	}

	
	
}
