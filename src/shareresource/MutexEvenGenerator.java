package shareresource;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用java.util.concurrent.locks.Locks锁,来保持线程同步
 * @author xugc
 *
 */
public class MutexEvenGenerator extends IntGenerator {

	private int currentEvenValue = 0;
	private Lock lock = new ReentrantLock();
	@Override
	public int next() {
		lock.lock();
		try {
			currentEvenValue++;
			Thread.yield();
			currentEvenValue++;
			return currentEvenValue;
		}finally{
			lock.unlock();
		}
	}
	
	public static void main(String[] args){
		EvenChecker.test(new MutexEvenGenerator());
		//EvenChecker.test(new SynEvenGenerator());
	}
	
}

class SynEvenGenerator extends IntGenerator{

	private int currentEvenValue=0;
	private Object object=new Object();
	@Override
	public int next() {
		synchronized (object) {
			currentEvenValue++;
			Thread.yield();
			currentEvenValue++;
			return currentEvenValue;
		}
		//return currentEvenValue; //如果return 语句不在synchronized保护的语句块中,会导致冲突.
	}
	
}