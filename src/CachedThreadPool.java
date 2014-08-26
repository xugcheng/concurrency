import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * 使用java.util.concurrency.Executor执行器来管理任务
 * @author xugc
 *
 */
public class CachedThreadPool {
	
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		for(int i=0;i<5;i++)
		{
			exec.execute(new LiftOff());
		}
		exec.shutdown();
	}

}
