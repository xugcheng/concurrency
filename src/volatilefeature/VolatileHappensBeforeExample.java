package volatilefeature;

public class VolatileHappensBeforeExample {
	@Override
	public String toString() {
		return "VolatileHappensBeforeExample [a=" + a + ", flag=" + flag + "]";
	}

	int  a = 1;								//共享普通变量
	private volatile boolean flag = false;	//volatile变量
	
	public void write()
	{
		a = 2;
		flag = true;
	}
	
	public void read()
	{
		if(flag)
		{
			int i = a;
			System.out.println("i="+i);
			a = i*2;
		}
	}
	
	public static void main(String[] args)
	{
		final VolatileHappensBeforeExample vhbe = new VolatileHappensBeforeExample();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				vhbe.write();
			}
		}).start();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				vhbe.read();
			}
		}).start();
		
		while(Thread.activeCount()>1){
			Thread.yield();
		}
		System.out.println(vhbe);
	}
}	
