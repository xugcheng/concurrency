package shareresource;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 消费者任务--检测偶数的有效性
 * @author xugc
 *
 */
public class EvenChecker implements Runnable {
	private IntGenerator generator;
	private final int id;
	public EvenChecker(IntGenerator g,int ident){
		generator = g;
		id = ident;
	}
	@Override
	public void run() {
		while(!generator.isCanceled()){
			int val = generator.next();
			if(generator instanceof SynchronizeEvenGenerator){
				System.out.println("EvenChecker "+id+","+val);
			}
			if(val % 2 !=0){
				System.out.println("EvenChecker "+id+","+val+" not even!");
				generator.cancel();
			}
		}
	}
	//
	public static void test(IntGenerator gp,int count){
		System.out.println("Press Control-C to exit");
		ExecutorService exec = Executors.newCachedThreadPool();
		for(int i=0;i<count;i++){
			exec.execute(new EvenChecker(gp, i));
		}
		exec.shutdown();
	}
	//
	public static void test(IntGenerator gp){
		test(gp,10);
	}
	
}
