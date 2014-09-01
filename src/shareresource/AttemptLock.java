package shareresource;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AttemptLock {
	private Lock lock = new ReentrantLock();
	public void untimed(){
		boolean captured = lock.tryLock();
		try{
			System.out.println("tryLock():"+captured);
		}finally{
			if(captured){
				lock.unlock();
			}
		}
	}
	
	public void timed(){
		boolean captured = false;
		try {
			captured = lock.tryLock(2, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		try{
			System.out.println("tryLock(2, TimeUnit.SECONDS):"+captured);
		}finally{
			if(captured){
				lock.unlock();
			}
		}
	}
	
	public static void main(String[] args) {
		final AttemptLock attemptLock = new AttemptLock();
		attemptLock.untimed();
		attemptLock.timed();
		new Thread(){
			{setDaemon(true);}
			@Override
			public void run(){
				attemptLock.lock.lock();
//				try {
//					attemptLock.lock.lockInterruptibly();
//				} catch (InterruptedException e1) {
//					e1.printStackTrace();
//				}
				System.out.println("acquired");
				try {
					TimeUnit.SECONDS.sleep(5);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				attemptLock.lock.unlock();
			}
		}.start();
		Thread.yield();
		attemptLock.untimed();
		attemptLock.timed();
		
	}
	
}
