package threadlocal;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * ThreadLocal 设置变量的线程本地存储.
 * @author xugc
 *
 */
public class ThreadLocalVariableHolder {
	
	private static ThreadLocal<Integer> value = new ThreadLocal<Integer>(){
		private Random random = new Random(47);
		protected synchronized Integer initialValue(){
			return random.nextInt(10000);
		}
	};
	
	public static void increment(){
		value.set(value.get()+1);
	}
	
	private static ThreadLocal<String> str = new ThreadLocal<String>();
	
	public static int get(){
		return value.get();
	}
	
	public static String getStr(){
		return str.get();
	}
	
	public static void main(String[] args){
		ExecutorService exec = Executors.newCachedThreadPool();
		for(int i=0;i<5;i++){
			exec.execute(new Accessor(i));
		}
		exec.execute(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("str:"+ThreadLocalVariableHolder.getStr()); //没有赋值的ThreadLocal变量,为null
			}
		});
		
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();	
		}
		exec.shutdownNow();
	}
}


class Accessor implements Runnable{
	
	private final int id;
	
	public Accessor(int id){
		this.id = id;
	}
	
	@Override
	public void run() {
		while(!Thread.currentThread().isInterrupted()){
			ThreadLocalVariableHolder.increment();
			System.out.println(this);
			Thread.yield();
		}
	}
	
	public String toString(){
		return "#"+id+" : "+ThreadLocalVariableHolder.get();
	}
	
}