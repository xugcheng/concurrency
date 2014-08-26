package join;

import java.util.concurrent.TimeUnit;

class Sleeper extends Thread{
	private int duration;
	public Sleeper(String name,int sleepTime){
		super(name);
		duration = sleepTime;
		start();
	}
	@Override
	public void run(){
		try {
			System.out.println(getName()+" is started.");
			TimeUnit.MILLISECONDS.sleep(duration);
		} catch (InterruptedException e) {
			System.out.println(getName()+" was interrupted."+" isInterrupted():"+isInterrupted());
		}
		System.out.println(getName()+" has awakened.");
	}
}

class Joiner extends Thread{
	private Sleeper sleeper;
	public Joiner(String name,Sleeper sleeper){
		super(name);
		this.sleeper = sleeper;
		start();
	}
	@Override
	public void run(){
		try {
			System.out.println(getName()+" is started");
			sleeper.join();
		} catch (InterruptedException e) {
			System.out.println(getName()+" Interrupted");
		}
		System.out.println(getName()+" join completed.");
	}
}

public class Joining {
	public static void main(String[] args) {
		Sleeper slpeey = new Sleeper("spleey", 1500);
		Sleeper grumpy = new Sleeper("grumpy", 1500);
		Joiner dopey= new Joiner("dopey", slpeey);
		Joiner doc = new Joiner("doc", grumpy);
		grumpy.interrupt();
	}
}
