import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class VolatileClass {
	boolean x = false;
	volatile boolean v = false;
	
	/**
	 * 一个线程写
	 */
	public void write(){
		v = true;
		x = true;
	}
	
	/**
	 * 一个线程读
	 */
	public void read(){
		if(v && !x){
			System.out.println("v && !x == true");
		}
	}
	
	public static void main(String[] args) {
		
		ExecutorService exec = Executors.newCachedThreadPool();
		
		for(int i=0;i<10000;i++){
			final VolatileClass vc = new VolatileClass();
			exec.execute(new Runnable() {	
				@Override
				public void run() {
					vc.write();
				}
			});
			exec.execute(new Runnable() {
				@Override
				public void run() {
					vc.read();
				}
			});
		}
		
		exec.shutdown();
	}

}
