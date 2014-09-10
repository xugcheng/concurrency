package criticalsection;

class DualSynch{
	private Object syncObject = new Object();
	
	public synchronized void f(){
		for(int i=0;i<5;i++){
			System.out.println("f()");
			Thread.yield();
		}
	}
	
	public void g(){
		synchronized (syncObject) { /*比起使用synchronized(this),不会阻塞f()*/
			for(int i=0;i<5;i++){
				System.out.println("g()");
				Thread.yield();
			}
		}
	}
}

public class SyncObject {
	
	public static void main(String[] args) {
		final DualSynch ds = new DualSynch();
		new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				ds.f();
			}
		}).start();
		ds.g();
	}

}
