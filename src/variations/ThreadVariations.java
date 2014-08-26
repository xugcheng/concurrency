package variations;

import java.util.concurrent.TimeUnit;

/**
 * named inner Thread
 * @author xugc
 *
 */
class InnerThread1{
	private int countDown = 5;
	private Inner inner;
	private class Inner extends Thread
	{
		public Inner(String name) {
			super(name);
			start();
		}
		@Override
		public void run()
		{
			while(true)
			{
				System.out.println(this);
				if(--countDown==0)
					return;
				try {
					TimeUnit.MILLISECONDS.sleep(10);
				} catch (InterruptedException e) {
					System.out.println("sleep() Interrupted.");
				}
			}
		}
		@Override
		public String toString()
		{
			return getName()+"("+countDown+")";
		}
	}
	public InnerThread1(String name)
	{
		inner = this.new Inner(name);
	}
}

/**
 * anonymous inner thread
 * @author xugc
 *
 */
class InnerThread2
{
	private int countDown = 5;
	private Thread t;
	public InnerThread2(String name){
		t = new Thread(name){
			public void run()
			{
				while(true)
				{
					System.out.println(this);
					if(--countDown==0) return;
					try {
						TimeUnit.MILLISECONDS.sleep(10);
					} catch (InterruptedException e) {
						System.out.println("sleep() Interrupted.");
					}
				}
			}
			public String toString()
			{
				return getName()+"("+countDown+")";
			}
		};
		t.start();
	}
}

class InnerRunnable1
{
	private int countDown = 5;
	private Inner inner;
	private class Inner implements Runnable
	{
		Thread t;
		public Inner(String name) {
			t = new Thread(this,name);
			t.start();
		}
		@Override
		public void run() {
			while(true)
			{
				System.out.println(this);
				if(--countDown==0) return;
				try {
					TimeUnit.MILLISECONDS.sleep(10);
				} catch (InterruptedException e) {
					System.out.println("sleep() Interrupted.");
				}
			}
			
		}
		public String toString()
		{
			return t.getName()+"("+countDown+")";
		}
	}
	
	public InnerRunnable1(String name)
	{
		inner = this.new Inner(name);
	}
}

class InnerRunnable2
{
	private int countDown = 5;
	private Thread t;
	public InnerRunnable2(String name)
	{
		t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true)
				{
					System.out.println(this);
					if(--countDown==0) return;
					try {
						TimeUnit.MILLISECONDS.sleep(10);
					} catch (InterruptedException e) {
						System.out.println("sleep() Interrupted.");
					}
				}
			}
			@Override
			public String toString()
			{
				return Thread.currentThread().getName()+"("+countDown+")";
			}
		},name);
		t.start();
	}
}

class ThreadMethod
{
	private int countDown = 5;
	private String name;
	private Thread t;
	public ThreadMethod(String name)
	{
		this.name = name;
	}
	public void runTask()
	{
		if(t==null)
		{
			t= new Thread(name){
				@Override
				public void run()
				{
					while(true)
					{
						System.out.println(this);
						if(--countDown == 0) return;
						try {
							TimeUnit.MILLISECONDS.sleep(10);
						} catch (InterruptedException e) {
							System.out.println("sleep() Interrupted.");
						}
					}
				}
				@Override
				public String toString()
				{
					return getName()+"("+countDown+")";
				}
			};
			t.start();
		}
	}
}

public class ThreadVariations {
	
	public static void main(String[] args) 
	{
		new InnerThread1("innerThread1");
		new InnerThread2("innerThread2");
		new InnerRunnable1("innerRunnable1");
		new InnerRunnable2("innerRunnable2");
		ThreadMethod threadMethod = new ThreadMethod("threadMethod");
		threadMethod.runTask();
		System.out.println(Thread.activeCount());
	}
	
}
