import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class SleepingTask extends LiftOff 
{
	@Override
	public void run()
	{
		Random random = new Random();
		int seconds = 1+random.nextInt(9);
		System.out.println(String.format("Thread %s sleep %s seconds.", Thread.currentThread().getName(),seconds));
		while(countDown-->0)
		{
			System.out.println(status());
			try {
				TimeUnit.SECONDS.sleep(seconds);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args)
	{
		ExecutorService exec = Executors.newCachedThreadPool();
		for(int i=0;i<10;i++)
		{
			exec.execute(new SleepingTask());
		}
		exec.shutdown();
	}
}
