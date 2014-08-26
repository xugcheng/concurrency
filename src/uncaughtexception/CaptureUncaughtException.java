package uncaughtexception;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

class MyExceptionThread implements Runnable{
	@Override
	public void run(){
		Thread t = Thread.currentThread();
		System.out.println("run by "+t);
		System.out.println("eh:"+t.getUncaughtExceptionHandler());
		throw new RuntimeException();
	}
}

class MyUncaughtExceptionHander implements UncaughtExceptionHandler{

	@Override
	public void uncaughtException(Thread t, Throwable e) {
		System.out.println("caught "+e);
	}
	
}

/**
 * 产生thread,设置默认的UnCaughtExceptionHander
 * @author xugc
 *
 */
class HanderThreadFactory implements ThreadFactory{

	@Override
	public Thread newThread(Runnable r) {
		// TODO Auto-generated method stub
		Thread t = new Thread(r);
		t.setUncaughtExceptionHandler(new MyUncaughtExceptionHander());
		return t;
	}
	
}

public class CaptureUncaughtException {

	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool(new HanderThreadFactory());
		exec.execute(new MyExceptionThread());
	}

}
