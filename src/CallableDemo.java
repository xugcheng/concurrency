import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class TaskWithResult implements Callable<String>
{
	private int id;
	
	public TaskWithResult(int id)
	{
		this.id = id;
	}
	
	@Override
	public String call() throws Exception 
	{
		return "result of TaskWithResult "+id;
	}
	
}

public class CallableDemo {
	public static void main(String[] args)
	{
		ExecutorService exec = Executors.newCachedThreadPool();
		List<Future<String>> futures = new ArrayList<Future<String>>();
		for(int i=0;i<10;i++)
		{
			futures.add(exec.submit(new TaskWithResult(i)));
		}
		try {
			for(Future<String> future : futures)
			{
				System.out.println(future.get());
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} finally{
			exec.shutdown();
		}
	}
}

class TaskWithGeneric<T> implements Callable<T>{

	@Override
	public T call() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}