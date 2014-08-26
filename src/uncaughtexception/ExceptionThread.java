package uncaughtexception;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExceptionThread implements Runnable{

	@Override
	public void run() {
		System.out.println("ExceptionThread start");
		throw new RuntimeException();
	}
	
	public static void main(String[] args){
		try {
			ExecutorService exec = Executors.newCachedThreadPool();
			exec.execute(new ExceptionThread());
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}
	
}
