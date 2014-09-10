package criticalsection;

/**
 * 在同一个对像上加锁建立临界区,只能同时运行一个方法.
 * @author xugc
 *
 */
class MultiSync{
	private Object syncObject1 = new Object();
	private Object syncObject2 = new Object();
	public void a(){
		synchronized (syncObject1) {
			for(int i=0;i<5;i++){
				System.out.println("a()"+i);
				Thread.yield();
			}
		}
	}
	
	public void b(){
		synchronized (syncObject1) {
			for(int i=0;i<5;i++){
				System.out.println("b()"+i);
				Thread.yield();
			}
		}
	}
	
	public void c(){
		synchronized (syncObject2) {
			for(int i=0;i<5;i++){
				System.out.println("c()"+i);
				Thread.yield();
			}
		}
	}
}

public class SyncObject2 {
	public static void main(String[] args){
		final MultiSync ms = new MultiSync();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				ms.a();
			}
		}).start();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				ms.b();
			}
		}).start();
		
		ms.c();
	}
}
